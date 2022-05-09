-- --------------------------------------------------------
--
-- Table structure for table `postcodelatlng`
--

CREATE TABLE IF NOT EXISTS `postcodelatlng` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `postcode` varchar(8) NOT NULL,
  `latitude` varchar(10) NULL,
  `longitude` varchar(11) NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1;

CREATE INDEX idx_postcode ON postcodelatlng (postcode);