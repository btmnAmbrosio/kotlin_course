package course.intermediate.notes.tasks

import androidx.lifecycle.ViewModel
import course.intermediate.notes.models.Task
import course.intermediate.notes.tasks.ITaskModel
import course.intermediate.notes.models.Todo
import course.intermediate.notes.tasks.TaskLocalModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import javax.inject.Inject
import toothpick.Toothpick
import toothpick.config.Module
import course.intermediate.notes.foundations.ApplicationModule
import course.intermediate.notes.foundations.ApplicationScope

class TaskViewModel : ViewModel(), TaskListViewContract {

    @Inject
    lateinit var model: ITaskModel

    private val _taskListLiveData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskListLiveData: LiveData<MutableList<Task>> = _taskListLiveData

    init {

        Toothpick.inject(this, ApplicationScope.scope)
        _taskListLiveData.postValue(model.getFakeData())
    }


    override fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {
        _taskListLiveData.value?.get(taskIndex)?.todos?.get(todoIndex)?.isComplete = isComplete
    }
}