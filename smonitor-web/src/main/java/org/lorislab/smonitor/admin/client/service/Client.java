/*
 * Copyright 2013 lorislab.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lorislab.smonitor.admin.client.service;

import org.jboss.errai.common.client.api.ErrorCallback;
import org.jboss.errai.common.client.api.RemoteCallback;

/**
 *
 * @author Andrej Petras
 */
public interface Client<T> {
  
  /**
   * Returns an instance of the proxy that can invoke service methods on the
   * service described by {@code T}.
   *
   * @return an instance of the proxy that can invoke service methods on the
   *         service described by {@code T}.
   */
  public T call();

  /**
   * Returns an instance of the proxy that can invoke service methods on the
   * service described by {@code T}.
   *
   * @param callback
   *          the callback to be invoked when the remote call has completed in
   *          success. In the case of an error, a default error callback will be
   *          notified. Which one depends on the proxy implementation.
   *          <p>
   *          The provided callback must not be null.
   * @return an instance of the proxy that can invoke service methods on the
   *         service described by {@code T}.
   */
  public T call(RemoteCallback<?> callback);

  /**
   * Returns an instance of the proxy that can invoke service methods on the
   * service described by {@code T}.
   *
   * @param callback
   *          the callback to be invoked when the remote call has completed in
   *          success. In the case of an error, a default error callback will be
   *          notified. Which one depends on the proxy implementation.
   *          <p>
   *          The callback must not be null.
   * @param errorCallback
   *          the callback to be invoked when the remote call has completed
   *          in failure. No callback is invoked in the case of success.
   *          <p>
   *          The provided callback must not be null.
   * @return an instance of the proxy that can invoke service methods on the
   *         service described by {@code T}.
   */
  public T call(RemoteCallback<?> callback, ErrorCallback<?> errorCallback);
  
//    public static <R> ServerService serverService(final RemoteCallback<R> callback, final RestServiceExceptionCallback errorCallback) {
//        return create(ServerService.class, callback, errorCallback);
//    }
//    
//    public static <T, R> T create(final Class<T> remoteService, final RemoteCallback<R> callback,
//      final RestServiceExceptionCallback errorCallback) {    
//        return RestClient.create(remoteService, callback, errorCallback);
//     }    
}
