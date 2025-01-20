-- 删除已存在的表
-- t_user: 用户信息表
DROP TABLE IF EXISTS user;
-- t_lecture: 讲座信息表
DROP TABLE IF EXISTS lecture;
-- t_reservation: 讲座预约表
DROP TABLE IF EXISTS reservation;
-- t_feedback: 讲座评价表
DROP TABLE IF EXISTS feedback;
-- t_news: 资讯信息表
DROP TABLE IF EXISTS news;
-- t_news_category: 资讯分类表
DROP TABLE IF EXISTS news_category;
-- t_announcement: 公告信息表
DROP TABLE IF EXISTS announcement;
-- t_check_in: 讲座签到表
DROP TABLE IF EXISTS check_in;

-- t_user: 用户信息表
CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    username VARCHAR(50) NOT NULL COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    role VARCHAR(20) NOT NULL COMMENT '角色(admin-管理员/lecturer-讲师/student-学生)',
    name VARCHAR(50) NOT NULL COMMENT '真实姓名',
    email VARCHAR(100) COMMENT '邮箱',
    phone VARCHAR(20) COMMENT '电话',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

-- t_lecture: 讲座信息表
CREATE TABLE lecture (
    id INT NOT NULL AUTO_INCREMENT COMMENT '讲座ID',
    title VARCHAR(200) NOT NULL COMMENT '讲座标题',
    description TEXT COMMENT '讲座描述',
    lecturer_id INT NOT NULL COMMENT '讲师ID',
    location VARCHAR(200) NOT NULL COMMENT '地点',
    start_time DATETIME NOT NULL COMMENT '开始时间',
    end_time DATETIME NOT NULL COMMENT '结束时间',
    capacity INT NOT NULL COMMENT '容量',
    status TINYINT NOT NULL DEFAULT 0 COMMENT '状态(0-未开始/1-进行中/2-已结束)',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY idx_lecturer (lecturer_id),
    KEY idx_start_time (start_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='讲座信息表';

-- t_reservation: 讲座预约表
CREATE TABLE reservation (
    id INT NOT NULL AUTO_INCREMENT COMMENT '预约ID',
    lecture_id INT NOT NULL COMMENT '讲座ID',
    student_id INT NOT NULL COMMENT '学生ID',
    status TINYINT NOT NULL DEFAULT 1 COMMENT '状态(1-已预约/0-已取消)',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_lecture_student (lecture_id, student_id),
    KEY idx_student (student_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='讲座预约表';

-- t_feedback: 讲座评价表
CREATE TABLE feedback (
    id INT NOT NULL AUTO_INCREMENT COMMENT '评价ID',
    lecture_id INT NOT NULL COMMENT '讲座ID',
    student_id INT NOT NULL COMMENT '学生ID',
    rating TINYINT NOT NULL COMMENT '评分(1-5分)',
    comment TEXT COMMENT '评价内容',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_lecture_student (lecture_id, student_id),
    KEY idx_lecture (lecture_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='讲座评价表';

-- t_news: 资讯信息表
CREATE TABLE news (
    id INT NOT NULL AUTO_INCREMENT COMMENT '资讯ID',
    title VARCHAR(200) NOT NULL COMMENT '资讯标题',
    content TEXT NOT NULL COMMENT '资讯内容',
    category_id INT NOT NULL COMMENT '分类ID',
    cover_url VARCHAR(255) COMMENT '封面图片URL',
    author_id INT NOT NULL COMMENT '作者ID',
    status TINYINT NOT NULL DEFAULT 0 COMMENT '状态(0-草稿/1-已发布/2-已下线)',
    is_top TINYINT NOT NULL DEFAULT 0 COMMENT '是否置顶(0-否/1-是)',
    view_count INT NOT NULL DEFAULT 0 COMMENT '阅读量',
    publish_time DATETIME COMMENT '发布时间',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY idx_category (category_id),
    KEY idx_author (author_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资讯信息表';

-- t_news_category: 资讯分类表
CREATE TABLE news_category (
    id INT NOT NULL AUTO_INCREMENT COMMENT '分类ID',
    name VARCHAR(50) NOT NULL COMMENT '分类名称',
    sort INT NOT NULL DEFAULT 0 COMMENT '排序号',
    status TINYINT NOT NULL DEFAULT 1 COMMENT '状态(0-禁用/1-启用)',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资讯分类表';

-- t_announcement: 公告信息表
CREATE TABLE announcement (
    id INT NOT NULL AUTO_INCREMENT COMMENT '公告ID',
    title VARCHAR(200) NOT NULL COMMENT '公告标题',
    content TEXT NOT NULL COMMENT '公告内容',
    publisher_id INT NOT NULL COMMENT '发布人ID',
    status TINYINT NOT NULL DEFAULT 0 COMMENT '状态(0-草稿/1-已发布/2-已下线)',
    is_top TINYINT NOT NULL DEFAULT 0 COMMENT '是否置顶(0-否/1-是)',
    start_time DATETIME COMMENT '生效时间',
    end_time DATETIME COMMENT '失效时间',
    view_count INT NOT NULL DEFAULT 0 COMMENT '阅读量',
    publish_time DATETIME COMMENT '发布时间',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY idx_publisher (publisher_id),
    KEY idx_status_time (status, start_time, end_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公告信息表';

-- t_check_in: 讲座签到表
CREATE TABLE check_in (
    id INT NOT NULL AUTO_INCREMENT COMMENT '签到ID',
    lecture_id INT NOT NULL COMMENT '讲座ID',
    student_id INT NOT NULL COMMENT '学生ID',
    check_in_time DATETIME NOT NULL COMMENT '签到时间',
    check_in_type TINYINT NOT NULL COMMENT '签到类型(1-位置/2-手动)',
    location VARCHAR(255) COMMENT '签到位置',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_lecture_student (lecture_id, student_id),
    KEY idx_lecture_time (lecture_id, check_in_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='讲座签到表';

-- 初始化管理员账号
INSERT INTO user (username, password, role, name, email, phone) VALUES 
('admin', '$2a$10$N.ZOn9G6/YLFixAOPMg/h.z7pCu6v2XyFDtC4q.jeeGM/TEZhPy7i', 'admin', '系统管理员', 'admin@example.com', '13800138000');

-- 初始化资讯分类
INSERT INTO news_category (name, sort, status) VALUES 
('讲座预告', 1, 1),
('讲座回顾', 2, 1),
('通知公告', 3, 1),
('学术资讯', 4, 1);

-- 初始化一条系统公告
INSERT INTO announcement (title, content, publisher_id, status, is_top, start_time, end_time, publish_time) VALUES 
('系统上线公告', '欢迎使用高校讲座预约管理系统，本系统提供讲座预约、资讯查看等功能。', 1, 1, 1, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), NOW()); 