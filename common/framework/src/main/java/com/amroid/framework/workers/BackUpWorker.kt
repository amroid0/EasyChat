package com.amroid.framework.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.amroid.framework.BackupUseCaseInvoker
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.TimeUnit

@HiltWorker
class BackUpWorker @AssistedInject constructor(
    @Assisted val appContext: Context, @Assisted private val params: WorkerParameters,
    private val invoker: BackupUseCaseInvoker
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        try {
            invoker.invoke()
            return Result.success()
        } catch (e: Exception) {
            if (runAttemptCount < MAX_TRIES) {
                return Result.retry()
            }
        }
        return Result.failure()
    }

    companion object {
        private const val MAX_TRIES = 3
        fun startWorker(context: Context) {
            val constraints =
                Constraints.Builder().setRequiredNetworkType(NetworkType.UNMETERED).build()
            val request = PeriodicWorkRequestBuilder<BackUpWorker>(7, TimeUnit.DAYS).setConstraints(
                constraints
            ).setBackoffCriteria(BackoffPolicy.LINEAR, 15, TimeUnit.MINUTES).build()
            WorkManager.getInstance(context).enqueue(request)
        }
    }
}