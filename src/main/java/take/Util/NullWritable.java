package take.Util;

import java.io.Serializable;

/**
 * 功能描述：
 *
 * @author EO
 * @date 2021/5/19 22:48
 */
public class NullWritable implements Serializable {
    private static final long serialVersionUID = -8191640400484155111L;
    private static NullWritable instance = new NullWritable();

    private NullWritable() {
    }

    public static NullWritable nullWritable() {
        return instance;
    }
}
