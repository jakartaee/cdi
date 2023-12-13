/*
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jakarta.enterprise.inject.spi;

/**
 * <p>
 * The type of the final event the container fires after it has finished processing requests and destroyed all contexts. If any
 * observer method of the {@code BeforeShutdown} event throws an exception, the exception is ignored by the container.
 * </p>
 *
 * <p>
 * CDI Lite implementations are not required to provide support for Portable Extensions.
 * </p>
 *
 * @author David Allen
 */
public interface BeforeShutdown {
}
