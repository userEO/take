package take.Util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import take.nettyserver.RPCProxyFactoryBean;

import java.util.List;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2021/5/19 22:50
 */
public class NettyBeanScanner {
    private DefaultListableBeanFactory beanFactory;

    private String basePackage;

    private String clientName;

    public NettyBeanScanner(String basePackage, String clientName) {
        this.basePackage = basePackage;
        this.clientName = clientName;
    }


    /**
     * 注册Bean到Spring的bean工厂
     */
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
        // 加载远程服务的接口
        List<String> resolverClass = PackageClassUtils.resolver(basePackage);
        for (String clazz : resolverClass) {
            String simpleName;
            if (clazz.lastIndexOf('.') != -1) {
                simpleName = clazz.substring(clazz.lastIndexOf('.') + 1);
            } else {
                simpleName = clazz;
            }
            BeanDefinitionBuilder gd = BeanDefinitionBuilder.genericBeanDefinition(RPCProxyFactoryBean.class);
            gd.addPropertyValue("interfaceClass", clazz);
            gd.addPropertyReference("nettyClient", clientName);
            this.beanFactory.registerBeanDefinition(simpleName, gd.getRawBeanDefinition());
        }
    }
}
