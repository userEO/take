package take.Util;

import java.io.Serializable;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2021/5/19 22:45
 */
public class MethodInvokeMeta implements Serializable {
    private static final long serialVersionUID = 8379109667714148890L;
    //接口
    private Class<?> interfaceClass;
    //方法名
    private String methodName;
    //参数
    private Object[] args;
    //返回值类型
    private Class<?> returnType;
    //参数类型
    private Class<?>[] parameterTypes;

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Class<?> getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Class getReturnType() {
        return returnType;
    }

    public void setReturnType(Class returnType) {
        this.returnType = returnType;
    }
}
