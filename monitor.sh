#!/bin/bash

# 获取最后一个参数作为文件路径
file_path="${@: -1}"

# 检查文件是否存在
if [ ! -f "$file_path" ]; then
    echo "文件不存在，使用默认路径，logs/app.monitor"
    file_path="logs/app.monitor"
#    exit 1
fi

# 检查参数数量
#if [ "$#" -ne 2 ]; then
#  echo "Usage: $0 -t <timeout> -n <num_chars>"
#  exit 1
#fi

# 初始化变量
current_time=$(date +"%Y-%m-%d")
#echo "Current time: $current_time"

num_chars=100

# 处理选项
while [ "$1" != "" ]; do
  case $1 in
    -t | --current_time )
      shift
      if [ "$1" = "" ]; then
        echo "current_time value is missing."
        exit 1
      fi
      current_time=$1
      ;;
    -n | --num-chars )
      shift
      if [ "$1" = "" ]; then
        echo "Number of characters is missing."
        exit 1
      fi
      num_chars=$1
      ;;
    * )
#      echo "Unknown option: $1"
#      exit 1
      ;;
  esac
  shift
done

echo "Current time: $current_time"
echo "file_path: $file_path"
# 检查是否所有必需的参数都已设置
#if [ "$timeout" -eq 0 ] || [ "$num_chars" -eq 0 ]; then
#  echo "Both -t and -n options are required."
#  exit 1
#fi


# 从第一行提取时间
#file_path='.idea/logs/app.monitor'
#current_time=$(awk 'NR==1 {print $1}' $file_path)

# 筛选出大于该时间的数据，并按照最后一列排序输出前10列
awk -v current_time="$current_time" '
    BEGIN {
        FS=OFS=" ";
    }
#    NR == 1 {current_time = $1; next}  # 只在第一行提取时间
#    $1 >= current_time {
    {
        timeStamp = $1 " " $2;
        # 提取耗时字段
        time = $NF
        # 添加行号
#        printf "%s\t%d\t%s\n", time, NR, $0
        if(timeStamp >= current_time){
          printf "%s\t%d\t%s\n", time, NR, $0
        }
    }
' $file_path | sort -k1,1nr | head -n $num_chars  | awk '{
    # 读取排序后的行
    time = $1
    row_num = $2
    original_line = $3
    print original_line,$4,$5,$6,$7
}'