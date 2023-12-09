-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2023 at 05:52 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cnw_cv_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `basicinfo`
--

CREATE TABLE `basicinfo` (
  `basic_info_id` varchar(150) NOT NULL,
  `name` text NOT NULL,
  `github` text NOT NULL,
  `email` text NOT NULL,
  `address` text NOT NULL,
  `phone` text NOT NULL,
  `about_me` text NOT NULL,
  `cv_id` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cv`
--

CREATE TABLE `cv` (
  `cv_id` varchar(150) NOT NULL,
  `user_Id` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `education`
--

CREATE TABLE `education` (
  `education_id` varchar(150) NOT NULL,
  `education_name` text NOT NULL,
  `time` text NOT NULL,
  `cv_id` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `logindata`
--

CREATE TABLE `logindata` (
  `login_data_id` varchar(150) NOT NULL,
  `user_id` varchar(150) NOT NULL,
  `password_hash` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `project_id` varchar(150) NOT NULL,
  `project_name` text NOT NULL,
  `time` text NOT NULL,
  `num_of_member` int(11) NOT NULL,
  `github` text NOT NULL,
  `cv_id` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `projectdescription`
--

CREATE TABLE `projectdescription` (
  `description_id` varchar(150) NOT NULL,
  `description` text NOT NULL,
  `project_id` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `skill`
--

CREATE TABLE `skill` (
  `skill_id` varchar(150) NOT NULL,
  `skill_name` text NOT NULL,
  `cv_id` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` varchar(150) NOT NULL,
  `username` text NOT NULL,
  `email` text NOT NULL,
  `firstname` text NOT NULL,
  `lastname` text NOT NULL,
  `role_id` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `userrole`
--

CREATE TABLE `userrole` (
  `role_id` varchar(150) NOT NULL,
  `role_name` varchar(1000) NOT NULL,
  `permission` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `basicinfo`
--
ALTER TABLE `basicinfo`
  ADD PRIMARY KEY (`basic_info_id`),
  ADD KEY `FK_basic_info_cv_id` (`cv_id`);

--
-- Indexes for table `cv`
--
ALTER TABLE `cv`
  ADD PRIMARY KEY (`cv_id`),
  ADD KEY `FK_cv_user_id` (`user_Id`);

--
-- Indexes for table `education`
--
ALTER TABLE `education`
  ADD PRIMARY KEY (`education_id`),
  ADD KEY `FK_education_cv_id` (`cv_id`);

--
-- Indexes for table `logindata`
--
ALTER TABLE `logindata`
  ADD PRIMARY KEY (`login_data_id`),
  ADD KEY `FK_user_id` (`user_id`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`project_id`),
  ADD KEY `FK_project_cv_id` (`cv_id`);

--
-- Indexes for table `projectdescription`
--
ALTER TABLE `projectdescription`
  ADD PRIMARY KEY (`description_id`),
  ADD KEY `FK_project_description_project_id` (`project_id`);

--
-- Indexes for table `skill`
--
ALTER TABLE `skill`
  ADD PRIMARY KEY (`skill_id`),
  ADD KEY `FK_skill_cv_id` (`cv_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `FK_user_role_id` (`role_id`);

--
-- Indexes for table `userrole`
--
ALTER TABLE `userrole`
  ADD PRIMARY KEY (`role_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `basicinfo`
--
ALTER TABLE `basicinfo`
  ADD CONSTRAINT `FK_basic_info_cv_id` FOREIGN KEY (`cv_id`) REFERENCES `cv` (`cv_id`) ON DELETE CASCADE;

--
-- Constraints for table `cv`
--
ALTER TABLE `cv`
  ADD CONSTRAINT `FK_cv_user_id` FOREIGN KEY (`user_Id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE;

--
-- Constraints for table `education`
--
ALTER TABLE `education`
  ADD CONSTRAINT `FK_education_cv_id` FOREIGN KEY (`cv_id`) REFERENCES `cv` (`cv_id`) ON DELETE CASCADE;

--
-- Constraints for table `logindata`
--
ALTER TABLE `logindata`
  ADD CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE;

--
-- Constraints for table `project`
--
ALTER TABLE `project`
  ADD CONSTRAINT `FK_project_cv_id` FOREIGN KEY (`cv_id`) REFERENCES `cv` (`cv_id`) ON DELETE CASCADE;

--
-- Constraints for table `projectdescription`
--
ALTER TABLE `projectdescription`
  ADD CONSTRAINT `FK_project_description_project_id` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE CASCADE;

--
-- Constraints for table `skill`
--
ALTER TABLE `skill`
  ADD CONSTRAINT `FK_skill_cv_id` FOREIGN KEY (`cv_id`) REFERENCES `cv` (`cv_id`) ON DELETE CASCADE;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK_user_role_id` FOREIGN KEY (`role_id`) REFERENCES `userrole` (`Role_ID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
