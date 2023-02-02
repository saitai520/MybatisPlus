#!/bin/bash
# shellcheck disable=SC2006
db=`grep ':3306' src/main/resources/application-dev.yml | awk -F/ '{print $4}'  | awk -F? '{print $1}'`
echo "${db}"
mysqldump -h localhost -uroot -p123456  "${db}" > sql/"${db}".sql