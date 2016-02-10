#!/bin/sh
# Workbench Table Data copy script
# 
# Execute this to copy table data from a source RDBMS to MySQL.
# Edit the options below to customize it. You will need to provide passwords, at least.
# 
# Source DB: Mysql@209.126.96.72:3306 (MySQL)
# Target DB: Mysql@127.0.0.1:3306


# Source and target DB passwords
arg_source_password=
arg_target_password=
# Uncomment the following options according to your needs

# Whether target tables should be truncated before copy

#arg_truncate_target=--truncate-target
# Enable debugging output
#arg_debug_output=--log-level=debug3

/usr/bin/wbcopytables --mysql-source=Bloodbank@209.126.96.72:3306 --target=root@127.0.0.1:3306 --source-password="$arg_source_password" --target-password="$arg_target_password" $arg_truncate_target $arg_debug_output --table '`bloodbank`' '`profile`' '`bloodbank`' '`profile`' '`donorid`, `name`, `dob`, `age`, `bloodgroup`, `gender`, `spousename`, `education`, `occupation`, `resdoornoandstreetorroad`, `resbuildingname`, `resarea`, `resvillageortownorcity`, `restaluk`, `resdistrict`, `respincode`, `resphone`, `resmobile`, `resemail`, `officedoornoandstreetorroad`, `officebuildingname`, `officearea`, `officevillageortownorcity`, `officetaluk`, `officedistrict`, `officepincode`, `officephone`, `officemobile`, `officeemail`, `dor`, `nsdod`, `donor_type`, `willl_bday`, `will_wed_day`, `will_oth_day`, `will_term`'

