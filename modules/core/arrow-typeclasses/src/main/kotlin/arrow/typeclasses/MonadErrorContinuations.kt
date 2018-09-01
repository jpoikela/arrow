package arrow.typeclasses

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.RestrictsSuspension

@RestrictsSuspension
open class MonadErrorContinuation<F, A>(val ME: MonadError<F, Throwable>, override val context: CoroutineContext = EmptyCoroutineContext) :
  MonadContinuation<F, A>(ME), MonadError<F, Throwable> by ME {

  override fun resumeWithException(exception: Throwable) {
    returnedMonad = ME.raiseError(exception)
  }
}