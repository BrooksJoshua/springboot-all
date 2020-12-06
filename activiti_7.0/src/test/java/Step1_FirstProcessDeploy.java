import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

/**
 * created by Joshua.H.Brooks on 2020.12月.05.22.21
 * 部署第一个流程: 那么刚刚初始化后25张表中的如下3张就会插入部署完的流程记录信息.
 * tables affected:
 * 1. act_re_deployment 部署信息
 * 2. act_re_procdef 流程定义信息
 * 3. acr_ge_bytearray 加载的流程文件资源, bpmn, png, xml等
 *
 */
public class Step1_FirstProcessDeploy {

    @Test
    public void testDeploy(){

        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/process_01.bpmn")
                .addClasspathResource("diagram/process_01.png")
                .addClasspathResource("diagram/process_01.xml")
                .name("请假申请流程")
                .deploy();
    System.out.println(deployment.getName());
    System.out.println(deployment.toString());
    }

}
