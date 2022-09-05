springboot解决CORS(cross origin resource sharing)问题的三种方式
1. 在想要被共享的资源(也就是后端业务控制层类或者方法)上添加注解.`@CrossOrigin( value = "http://localhost:9091")` 该注解还有其他属性, 可以一并设置.
2. 