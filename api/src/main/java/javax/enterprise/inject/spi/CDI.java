package javax.enterprise.inject.spi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.inject.Instance;

/**
 * Provides access to the current container.
 * 
 * @author Pete Muir
 * 
 */
public abstract class CDI<T> implements Instance<T> {

   protected static final Set<CDIProvider> providers = new HashSet<CDIProvider>();

   /**
    * <p>
    * Get the CDI instance that provides access to the current container.
    * </p>
    * 
    * <p>
    * If there are no providers available, an {@link IllegalStateException} is thrown, otherwise the
    * first provider which can access the container is used.
    * </p>
    * 
    * @throws IllegalStateException if no CDI provider is available
    * 
    */
   public static <T> CDI<T> current() {
      CDI<T> cdi = null;

      if (providers.size() == 0) {
         findAllProviders();
      }
      for (CDIProvider provider : providers) {
         cdi = provider.getCDI();
         if (cdi != null)
            break;
      }
      if (cdi == null) {
         throw new IllegalStateException("Unable to access CDI");
      }
      return cdi;
   }

   // Helper methods

   private static void findAllProviders() {
      try {
         ClassLoader loader = Thread.currentThread().getContextClassLoader();
         Enumeration<URL> resources = loader.getResources("META-INF/services/" + CDIProvider.class.getName());
         Set<String> names = new HashSet<String>();
         while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            InputStream is = url.openStream();
            try {
               names.addAll(providerNamesFromReader(new BufferedReader(new InputStreamReader(is))));
            } finally {
               is.close();
            }
         }
         for (String s : names) {
            Class<CDIProvider> providerClass = (Class<CDIProvider>) loader.loadClass(s);
            providers.add(providerClass.newInstance());
         }
      } catch (IOException e) {
         throw new IllegalStateException(e);
      } catch (InstantiationException e) {
         throw new IllegalStateException(e);
      } catch (IllegalAccessException e) {
         throw new IllegalStateException(e);
      } catch (ClassNotFoundException e) {
         throw new IllegalStateException(e);
      }
   }

   private static final Pattern nonCommentPattern = Pattern.compile("^([^#]+)");

   private static Set<String> providerNamesFromReader(BufferedReader reader) throws IOException {
      Set<String> names = new HashSet<String>();
      String line;
      while ((line = reader.readLine()) != null) {
         line = line.trim();
         Matcher m = nonCommentPattern.matcher(line);
         if (m.find()) {
            names.add(m.group().trim());
         }
      }
      return names;
   }

   /**
    * Get the CDI BeanManager for the current context
    * 
    * @return
    */
   public abstract BeanManager getBeanManager();
}
