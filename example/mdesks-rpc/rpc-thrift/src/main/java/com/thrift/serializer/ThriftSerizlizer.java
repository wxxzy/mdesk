package com.thrift.serializer;

import com.thrift.service.User;
import org.apache.commons.codec.binary.Base64;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol;

public class ThriftSerizlizer {
    public static void main(String[] args){
        User user = new User();
        user.setName("mungpo");
        user.setEmail("www@126.com");

        TSerializer serializer = new TSerializer(new TBinaryProtocol.Factory());
        try {
            //序列化
            byte[] result = serializer.serialize(user);

            System.out.println(Base64.encodeBase64String(result));
        } catch (TException e) {
            e.printStackTrace();
        }

        byte[] data = Base64.decodeBase64("CwABAAAABm11bmdwbwsAAgAAAAt3d3dAMTI2LmNvbQA=");
        //反序列化
        TDeserializer deserializer = new TDeserializer(new TBinaryProtocol.Factory());
        try {
            User u = new User();
            deserializer.deserialize(u,data);
            System.out.println(u.getName());
            System.out.println(u.getEmail());

        } catch (TException e) {
            e.printStackTrace();
        }
    }

}
