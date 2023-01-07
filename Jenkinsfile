 /* Function for determining the connection to a specific port */
private static boolean checkOpenPort(String ipAddr, int openPort, int timeOutMillis) {
    // openPort =  22 - ssh, 80 or 443 - webserver, 25 - mailserver etc.
    try {
        Socket soc = new Socket()
        soc.connect(new InetSocketAddress(ipAddr, openPort), timeOutMillis);
        return true;
    }
    catch (IOException e) {
        return false;
    }
}
pipeline {
    agent any

    stages {
        stage('Test') {
           
            def file = new File('input.csv')
            def header = file.readLines()[0]
            header = header + ',IP reachable,Hostname Registered,SSH Enabled\n'
            def newfile = new File('output.csv')
            newfile.write(header)
            def rows = file.readLines().tail()*.split(',')
            int total = rows.size()
            Set ips = rows*.getAt(0)
            Set hosts = rows*.getAt(1)
            int i = 0
            while ( i < total ) {

                /* Check if PINGable */

                def checkping = "ping -c2 ${ips[i]}".execute()
                checkping.waitFor()
                def row = file.readLines()[i+1]
                row = checkping.exitValue() == 0 ? row + ',YES' : row + ',NO'
                    
                /* Check if hostname is resolving to ip */

                try {
                    InetAddress address = InetAddress.getByName(hosts[i])
                    // println address.getHostAddress()
                    row = row + ',YES'
                }
                catch (Exception e) {
                    // println "Address ${hosts[i]} does not exist"
                    row = row + ',NO'
                }

                /* Check if SSH port 22 is open and accepting connections */

                result = checkOpenPort(ips[i], 22, 3000) ? row + ',YES' : row + ',NO'
                newfile.append("${result}\n")

                i++
            }                        
        }
    }
}