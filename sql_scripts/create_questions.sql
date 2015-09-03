delimiter $$

CREATE TABLE `questions` (
  `sectionname` varchar(20) NOT NULL,
  `questionno` varchar(20) NOT NULL,
  `question` varchar(200) NOT NULL,
  `def_ans` varchar(10) NOT NULL,
  PRIMARY KEY (`sectionname`,`questionno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

