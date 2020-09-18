/*
 * JBoss, Home of Professional Open Source
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
package jakarta.enterprise.event;

/**
 * <p>
 * Distinguishes the various kinds of transactional {@linkplain Observes observer methods} from regular
 * observer methods which are notified immediately.
 * </p>
 * <p>
 * Transactional observer methods are observer methods which receive event notifications during the before or after completion
 * phase of the transaction in which the event was fired. If no transaction is in progress when the event is fired, they are
 * notified at the same time as other observers.
 * If the transaction is in progress, but {@link jakarta.transaction.Synchronization} callback cannot be registered due to the transaction being already
 * marked for rollback or in state where {@link jakarta.transaction.Synchronization} callbacks cannot be registered, the {@link #BEFORE_COMPLETION},
 * {@link #AFTER_COMPLETION} and {@link #AFTER_FAILURE} observer methods are notified at the same time as other observers,
 * but {@link #AFTER_SUCCESS} observer methods get skipped.
 * </p>
 *
 * @author Pete Muir
 * @author Gavin King
 * 
 */
@SuppressWarnings("ALL")
public enum TransactionPhase {

    /**
     * <p>
     * Identifies a regular observer method, called when the event is fired.
     * </p>
     */
    IN_PROGRESS,

    /**
     * <p>
     * Identifies a before completion observer method, called during the before completion phase of the transaction.
     * </p>
     * <p>
     * Transactional observer will be notified if there is no transaction in progress, or the transaction is in progress,
     * but {@link jakarta.transaction.Synchronization} callback cannot be registered due to the transaction being already
     * marked for rollback or in state where {@link jakarta.transaction.Synchronization} callbacks cannot be registered.
     * </p>
     */
    BEFORE_COMPLETION,

    /**
     * <p>
     * Identifies an after completion observer method, called during the after completion phase of the transaction.
     * </p>
     * <p>
     * Transactional observer will be notified if there is no transaction in progress, or the transaction is in progress,
     * but {@link jakarta.transaction.Synchronization} callback cannot be registered due to the transaction being already
     * marked for rollback or in state where {@link jakarta.transaction.Synchronization} callbacks cannot be registered.
     * </p>
     */
    AFTER_COMPLETION,

    /**
     * <p>
     * Identifies an after failure observer method, called during the after completion phase of the transaction, only when the
     * transaction fails.
     * </p>
     * <p>
     * Transactional observer will be notified will also get invoked if there is no transaction in progress, or the transaction is in progress,
     * but {@link jakarta.transaction.Synchronization} callback cannot be registered due to the transaction being already
     * marked for rollback or in state where {@link jakarta.transaction.Synchronization} callbacks cannot be registered.
     * </p>
     */
    AFTER_FAILURE,

    /**
     * <p>
     * Identifies an after success observer method, called during the after completion phase of the transaction, only when the
     * transaction completes successfully.
     * </p>
     */
    AFTER_SUCCESS

}
