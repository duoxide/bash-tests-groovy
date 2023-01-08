/* Function for determining the connection to a specific port */

def checkOpenPort(String ipAddr, int openPort, int timeOutMillis) {
    // openPort =  22 - ssh, 80 or 443 - webserver, 25 - mailserver etc.
    try {
        Socket soc = new Socket()
        soc.connect(new InetSocketAddress(ipAddr, openPort), timeOutMillis)
        return true
    }
    catch (IOException e) {
        return false
    }
}

def bashtest() {
    def file = new File("${WORKSPACE}/input.csv")
    def newfile = new File("${WORKSPACE}/output.csv")
    def rows = file.readLines()
    rows.eachWithIndex { String line, int index ->
        if ( index == 0 ) {
            newfile.write(line + ',IP reachable,Hostname Registered,SSH Enabled\n')
            return
        }
        def word = line.split(',')
        def ip = word[0]
        def host = word[1]

        /* Check if PINGable */

        def checkping = "/bin/ping -c2 ${ip}".execute()
        checkping.waitFor()
        line = checkping.exitValue() == 0 ? line + ',YES' : line + ',NO'

        /* Check if hostname is resolving to ip */

        try {
            InetAddress.getByName(host)
            line = line + ',YES'
        }
        catch (Exception e) {
            line = line + ',NO'
        }

        /* Check if SSH port 22 is open and accepting connections */

        result = checkOpenPort(ip, 22, 3000) ? line + ',YES' : line + ',NO'
        newfile.append("${result}\n")
    }
}
// bashtest()
return this