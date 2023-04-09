name = "Base Server"
description = "Apache Tomcat Server"

http {
    host = "192.168.1.10"
    port = 80
    secure = false
}

https {
    host = "192.168.1.10"
    port = 443
    secure = true
}

mappings = [
        {
            url = "/"
            active = true
        },
        {
            url = "/login"
            active = false
        }
]