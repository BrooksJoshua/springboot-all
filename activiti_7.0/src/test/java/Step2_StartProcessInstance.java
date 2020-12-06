import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

/**
 * created by Joshua.H.Brooks on 2020.12月.06.11.25
 */
public class Step2_StartProcessInstance {

    @Test
    public void test(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        ProcessInstance leaveApplication = runtimeService.startProcessInstanceByKey("LeaveApplication");//2501
        String deploymentId = leaveApplication.getDeploymentId();
        String processDefinitionId = leaveApplication.getProcessDefinitionId();
        String id = leaveApplication.getId();
        String activityId = leaveApplication.getActivityId();
        System.out.println("deploymentId:\t"+deploymentId+"\n"
                +"processDefinitionId:\t"+processDefinitionId+"\r\n"
                +"id:\t"+id+"\r\n"
                +"activityId:\t"+activityId);
    }

}
