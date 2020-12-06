import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * created by Joshua.H.Brooks on 2020.12月.06.14.44
 */
public class Step3_TaskQuery {

    @Test
    public void test(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = defaultProcessEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey("LeaveApplication")
                //.taskAssignee("zhangsan")
                .list();
        for(Task taskElement: list){
            System.out.println("流程实例ID:\t"+taskElement.getProcessInstanceId());
            System.out.println("任务ID:\t"+taskElement.getId());
            if(taskElement.getAssignee()==null){
                taskElement.setAssignee("张三");
            }
            System.out.println("任务负责人:\t"+taskElement.getAssignee());
            System.out.println("任务名称:\t"+taskElement.getName());
        }


    }
}
