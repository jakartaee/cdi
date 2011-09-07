package javax.enterprise.inject.spi;

import javax.enterprise.inject.Instance;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides access to the current container.
 * 
 * @author Pete Muir
 * 
 */
public abstract class CDI<T> implements Instance<T> {

   protected static volatile Set<CDIProvider> providers = null;

   private static final Object lock = new Object();

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
      if(providers == null) {
          synchronized (lock) {
              if(providers == null) {
                  findAllProviders();
              }
          }
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
      Set<CDIProvider> providers = new LinkedHashSet<CDIProvider>();
      try {
         final ClassLoader loader = CDI.class.getClassLoader();
         final Enumeration<URL> resources;
         if(loader != null) {
            resources = loader.getResources("META-INF/services/" + CDIProvider.class.getName());
         } else {
            //this should not happen unless the CDI api is on the boot class path
            resources = ClassLoader.getSystemResources("META-INF/services/" + CDIProvider.class.getName());
         }

         final Set<String> names = new HashSet<String>();
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
            final Class<CDIProvider> providerClass = (Class<CDIProvider>) Class.forName(s, true, loader);
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
      CDI.providers = Collections.unmodifiableSet(providers);
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
