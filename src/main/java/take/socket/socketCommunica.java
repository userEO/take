package take.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * 功能描述：socket通信工具类
 * 需要使用直接调用
 *
 * @author EO
 * @date 2019/11/22 9:58
 */
public class socketCommunica {
    public static final String host = "localhost";
    public static final int port = 8080;
    //报文头前缀 如需使用
    public static final String fileStart = "@@@@";
    //报文尾后缀   如需使用
    public static final String fileEnd = "####";
    /**
     * 获取报文内容的长度
     * @param bodyLength
     * @return
     */
    private static byte[] getBodyLength(int bodyLength) {

        byte[] result = new byte[] { '0', '0', '0', '0','0','0' };

        int tmp = 0;
        for (int i = 5; i >= 0; --i) {

            tmp = bodyLength % 10;
            result[i] = (byte) (Byte.valueOf(tmp + "") + 48);
            bodyLength /= 10;
        }

        return result;
    }
    /**
     * 往字节流添加字节流内容
     *
     @param left
      * @param right
     * @return
     */
    private static byte[] appendByte(byte[] left, byte[] right) {

        byte[] result = new byte[left.length + right.length];

        for (int i = 0; i < left.length; ++i) {
            result[i] = left[i];
        }

        for (int i = left.length; i < result.length; ++i) {
            result[i] = right[i - left.length];
        }

        return result;
    }
    /**
     * 字符串检索例子
     * @throws Exception
     */
    public static void socketBla() throws Exception {

/*        Socket socket = new Socket(host, port);

        long startTime = System.currentTimeMillis();

        //PrintWriter os = new PrintWriter(socket.getOutputStream());
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());

        DataInputStream is = new DataInputStream(socket.getInputStream());

        //通讯内容
        StringBuffer buffer = new StringBuffer();
        buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); //添加通讯XML报文头
        buffer.append("<message>"); //添加通讯XML跟结点
        buffer.append("<head>"); //
        buffer.append("<trxCode>9130010</trxCode>"); //添加通讯交易码
        buffer.append("<trxSeq>359942</trxSeq>");
        buffer.append("<trxDate>20130508</trxDate>");
        buffer.append("<trxTime>164914</trxTime>");
        buffer.append("<userid>demo1</userid>");
        buffer.append("<fromSource>02</fromSource>");
        buffer.append("<orgCode></orgCode>"); //
        buffer.append("<bizId>0000000000002</bizId>");
        //&lt;input>&lt;type>1&lt;/type>&lt;dataSourceList />&lt;content>艾山 买合苏木   &lt;/content>&lt;/input>
        buffer.append("</head>"); //添加检索请求
        buffer.append("<body>"); //添加检索请求
        // String blsreq = "<request><type>1011110</type><dataSources /><content>Usama Muhammed Awad Bin Laden</content></request>";
        String blsreq = "<request><reqSeq>001</reqSeq><type>1111111</type><dataSources /><content></content><nameContent>IRAN</nameContent><otherContent>hello 232E</otherContent><birthContent>20140803</birthContent><idContent>wrwer 23th</idContent><stateContent>china</stateContent><addrContent>hello 232 you KLCHSOWEWEE</addrContent></request>";
        blsreq =blsreq+ "<request><reqSeq>002</reqSeq><type>1111111</type><dataSources /><content>Divine Homes ZIMBAB Harare 12</content></request>";
        *//*   blsreq =blsreq+ "<request><reqSeq>003</reqSeq><type>1111111</type><dataSources /><content>Garcia Albert</content></request>";
        blsreq =blsreq+ "<request><reqSeq>004</reqSeq><type>1111111</type><dataSources /><content>IRAN</content></request>";
        blsreq =blsreq+ "<request><reqSeq>005</reqSeq><type>1111111</type><dataSources /><content>abce</content></request>";
        blsreq =blsreq+ "<request><reqSeq>006</reqSeq><type>1111111</type><dataSources /><content>hello</content></request>";*//*

        buffer.append(blsreq);
        buffer.append("</body>"); //
        buffer.append("</message>"); //添加跟结点结束

        byte[] a = buffer.toString().getBytes("UTF-8");
        int bodyLength = a.length;
        a = appendByte(getBodyLength(bodyLength), a);
        a = appendByte(fileStart.getBytes("UTF-8"), a);
        os.write(a);
        os.write(fileEnd.getBytes("UTF-8"));
        os.flush();

        System.out.println(new String(a, "UTF-8") + fileEnd);

        byte[] result = new byte[1200];
        int read = 0;
        while (true) {
            read = is.read(result);
            if (read < 0) {
                break;
            } else {
                System.out.print(new String(result, 0, read, "UTF-8"));
            }
        }
        long endTime = System.currentTimeMillis();

        System.out.println("\nBls Search Service Total time: " + (endTime - startTime) + "ms");
        os.close();
        is.close();
        socket.close();*/
    }
    /**
     * 报文检索例子
     * @throws Exception
     */
    public static void socketMsg(String benefitInfo) throws Exception {

        Socket socket = new Socket(host, port);

        long startTime = System.currentTimeMillis();

        //PrintWriter os = new PrintWriter(socket.getOutputStream());
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());

        DataInputStream is = new DataInputStream(socket.getInputStream());

        //通讯内容
        StringBuffer buffer = new StringBuffer();
        buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); //添加通讯XML报文头
        buffer.append("<message xmlns=\"nbp.bls.9130010.001.01\">"); //添加通讯XML跟结点
        buffer.append("<head>"); //
        buffer.append("<trxCode>9130010</trxCode>"); //添加通讯交易码
        buffer.append("<trxSeq>359942</trxSeq>");
        buffer.append("<trxDate>20130508</trxDate>");
        buffer.append("<trxTime>164914</trxTime>");
        buffer.append("<userid>demo1</userid>");
        buffer.append("<fromSource>02</fromSource>");
        buffer.append("<orgCode>016501</orgCode>"); //
        buffer.append("<bizId>0000000000002</bizId>");
        //&lt;input>&lt;type>1&lt;/type>&lt;dataSourceList />&lt;content>艾山 买合苏木   &lt;/content>&lt;/input>
        buffer.append("</head>"); //
        buffer.append("<body>"); //


        String refNo ="ref20111112323";
        String msg = "{1:F01CSCLCNBJAXXX0304154336}{2:I103BKCHCNBJXXXXN}{3:{108:031303040000ACK1}}{4:\r\n"
                + ":20:"
                + refNo
                + "\r\n"
                + ":23B:CRED\r\n"
                + ":32A:140701USD123,\r\n"
                + ":50K:123\r\n" + ":59:" + benefitInfo + "\r\n" + ":71A:OUR\r\n" + "-}";

        String blsreq = "<request><reqSeq>101</reqSeq><type>2</type><dataSources /><content>"
                + msg
                + "</content><messageRef>"
                + refNo + "</messageRef><currency>USD</currency><amount>123</amount></request>";

        buffer.append(blsreq);
        buffer.append("</body>"); //
        buffer.append("</message>"); //添加跟结点结束

        byte[] a = buffer.toString().getBytes("UTF-8");
        int bodyLength = a.length;
        a = appendByte(getBodyLength(bodyLength), a);
        a = appendByte(fileStart.getBytes("UTF-8"), a);
        os.write(a);
        os.write(fileEnd.getBytes("UTF-8"));
        os.flush();

        System.out.println(new String(a, "UTF-8") +fileEnd);

        byte[] result = new byte[1200];
        int read = 0;
        while (true) {
            read = is.read(result);
            if (read < 0) {
                break;
            } else {
                System.out.print(new String(result, 0, read, "UTF-8"));
            }
        }
        long endTime = System.currentTimeMillis();

        System.out.println("\nBls Search Service Total time: " + (endTime - startTime) + "ms");
        os.close();
        is.close();
        socket.close();
    }
}
