package com.company;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

interface IClock {
    LocalDateTime now();
}

class RealClock implements IClock {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}

class TimeServerClock implements IClock {

    @Override
    public LocalDateTime now() {
        Properties timeServers = createTimeServers();
        LocalDateTime time = null;
        try {
            time = getTimeWithLatencyFromTimeServers(timeServers);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return time;
    }


    private Properties createTimeServers() {
        Properties props = new Properties();
        props.put("0", "time-a-g.nist.gov");
        props.put("1", "time-b-g.nist.gov");
        props.put("2", "time-c-g.nist.gov");
        props.put("3", "time-d-g.nist.gov");
        props.put("4", "time-a-wwv.nist.gov");
        props.put("5", "time-b-wwv.nist.gov");

        return props;
    }

    private LocalDateTime getTimeWithLatencyFromTimeServers(Properties timeServers) throws IOException {
        NTPUDPClient timeClient = new NTPUDPClient();

        for (String serverAddress : timeServers.stringPropertyNames()) {
            InetAddress inetAddress = InetAddress.getByName(serverAddress);

            if (inetAddress != null) {
                long computerTimeWhenSentSignal = System.currentTimeMillis();
                TimeInfo timeInfo = timeClient.getTime(inetAddress);
                long computerTimeWhenGotSignalBack = System.currentTimeMillis();
                int timeLag = (int) (computerTimeWhenGotSignalBack - computerTimeWhenSentSignal) / 2;
                long currentTimeInMilliseconds = timeInfo.getReturnTime() + timeLag;
                Date dateTime = new Date(currentTimeInMilliseconds);

                LocalDateTime ldt = Instant.ofEpochMilli(currentTimeInMilliseconds).
                        atZone(ZoneId.systemDefault()).toLocalDateTime();
                return ldt;
            }
        }
        return null;
    }
}
