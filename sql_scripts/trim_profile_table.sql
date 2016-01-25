SELECT donorid, concat(resaddress, '-', respincode) as res, concat(officeaddress, '-', officepincode) as off FROM profile WHERE LENGTH(resaddress) != LENGTH(TRIM(resaddress)) or LENGTH(officeaddress) != LENGTH(TRIM(officeaddress));

update profile set resaddress = TRIM(resaddress), officeaddress = TRIM(officeaddress) where donorid <> ''