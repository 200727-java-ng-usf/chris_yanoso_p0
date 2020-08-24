package com.revature.p0.util;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;



public class ConnectionFactoryTest {


@Test
    public void connectionFactoryIsSingleton(){
    ConnectionFactory c1 = ConnectionFactory.getInstance();
    ConnectionFactory c2 = ConnectionFactory.getInstance();

    assertSame(c1,c2);

}

@Test
    public void ensureThatAConnectionIsEstablished() {
    Connection conn = ConnectionFactory.getInstance().getConnection();
    assertNotNull(conn);
}
}
