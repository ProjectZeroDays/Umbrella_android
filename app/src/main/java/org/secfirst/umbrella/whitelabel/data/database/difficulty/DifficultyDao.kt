package org.secfirst.umbrella.whitelabel.data.database.difficulty

import com.raizlabs.android.dbflow.kotlinextensions.modelAdapter
import com.raizlabs.android.dbflow.sql.language.SQLite
import kotlinx.coroutines.experimental.withContext
import org.secfirst.umbrella.whitelabel.data.database.lesson.Subject
import org.secfirst.umbrella.whitelabel.data.database.lesson.Subject_Table
import org.secfirst.umbrella.whitelabel.misc.AppExecutors

interface DifficultyDao {

    suspend fun save(difficultyPreferred: DifficultyPreferred) {
        withContext(AppExecutors.ioContext) {
            modelAdapter<DifficultyPreferred>().save(difficultyPreferred)
        }
    }

    suspend fun getSubjectByModule(modulePathId: String): Subject? = withContext(AppExecutors.ioContext) {
        SQLite.select()
                .from(Subject::class.java)
                .where(Subject_Table.module_path.`is`(modulePathId))
                .querySingle()
    }

    suspend fun getSubjectBy(subjectPathId: String): Subject? = withContext(AppExecutors.ioContext) {
        SQLite.select()
                .from(Subject::class.java)
                .where(Subject_Table.path.`is`(subjectPathId))
                .querySingle()
    }

    suspend fun getDifficultyBy(pathId : String): Difficulty? = withContext(AppExecutors.ioContext) {
        SQLite.select()
                .from(Difficulty::class.java)
                .where(Difficulty_Table.path.`is`(pathId))
                .querySingle()
    }
}