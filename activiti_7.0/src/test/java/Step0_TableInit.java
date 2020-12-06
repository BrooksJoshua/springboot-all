import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * created by Joshua.H.Brooks on 2020.12月.05.17.58
 */

public class Step0_TableInit {

    @Test
    public void tableCreation(){
        //创建一个ProccessEngineConfiguration对象
        //createProcessEngineConfigurationFromResource中第一个参数为配置文件名称，第二个参数为bean的名称
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml","processEngineConfiguration");
        //2.创建ProccessEngine对象
        ProcessEngine processEngine = configuration.buildProcessEngine();

    }




}
