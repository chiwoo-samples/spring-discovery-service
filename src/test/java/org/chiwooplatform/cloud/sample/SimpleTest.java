package org.chiwooplatform.cloud.sample;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.junit.Test;

public class SimpleTest
{
    @Test
    public void testGetHostname()
        throws Exception
    {
        System.out.printf( "\nInetAddress.getLocalHost().getHostName(): %s ",
                           InetAddress.getLocalHost().getHostName() );
        System.out.printf( "\nInetAddress.getLocalHost().getCanonicalHostName(): %s ",
                           InetAddress.getLocalHost().getCanonicalHostName() );
        System.out.printf( "\nInetAddress.getLoopbackAddress().getHostName(): %s ",
                           InetAddress.getLoopbackAddress().getHostName() );
    }

    @Test
    public void testGetHostnames()
        throws Exception
    {
        String hostName = null;
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        {
            while ( interfaces.hasMoreElements() )
            {
                NetworkInterface nic = interfaces.nextElement();
                System.out.printf( "\n --- nic.getDisplayName(): %s - %s  loopback: %s, virtual: %s, multicast: %s, up: %s, p2p: %s",
                                   nic.getDisplayName(), nic.getName(), nic.isLoopback(), nic.isVirtual(),
                                   nic.supportsMulticast(), nic.isUp(), nic.isPointToPoint() );
                Enumeration<InetAddress> addresses = nic.getInetAddresses();
                while ( addresses.hasMoreElements() )
                {
                    InetAddress address = addresses.nextElement();
                    if ( !address.isLoopbackAddress() )
                    {
                        hostName = address.getHostName();
                        System.out.printf( "\n --- HostName: %s ", hostName );
                    }
                    System.out.printf( "\n HostName: %s ", hostName );
                }
            }
        }
    }
}
