input {
    tcp {
        mode => "server"
        host => "0.0.0.0"   //日志输入地址(所有外网地址)，也指定具体输入地址
        port => 4560      //日志输入端口
        codec => json_lines
    }
}
output{
  elasticsearch {
    hosts => ["elasticsearch:9200"]
    action => "index"
    index => "applog"
    }
  stdout {
    codec => rubydebug
    }
}
