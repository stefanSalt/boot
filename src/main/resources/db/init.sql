-- 创建数据库
CREATE DATABASE IF NOT EXISTS questionnaire_system DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE questionnaire_system;

-- 用户表
CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    username VARCHAR(50) NOT NULL COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    nickname VARCHAR(50) COMMENT '昵称',
    email VARCHAR(100) COMMENT '邮箱',
    phone VARCHAR(20) COMMENT '手机号',
    avatar VARCHAR(255) COMMENT '头像URL',
    role VARCHAR(20) NOT NULL DEFAULT 'USER' COMMENT '角色(ADMIN/USER)',
    status TINYINT NOT NULL DEFAULT 1 COMMENT '状态(0禁用,1启用)',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (id),
    UNIQUE KEY uk_username (username),
    UNIQUE KEY uk_email (email),
    UNIQUE KEY uk_phone (phone)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 问卷表
CREATE TABLE questionnaire (
    id INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    title VARCHAR(200) NOT NULL COMMENT '问卷标题',
    description TEXT COMMENT '问卷说明',
    creator_id INT NOT NULL COMMENT '创建者ID',
    status VARCHAR(20) NOT NULL DEFAULT 'DRAFT' COMMENT '状态(DRAFT/PUBLISHED/CLOSED)',
    access_code VARCHAR(32) NOT NULL COMMENT '问卷访问码',
    access_url VARCHAR(255) NOT NULL COMMENT '问卷访问链接',
    password VARCHAR(32) COMMENT '访问密码',
    start_time DATETIME COMMENT '开始时间',
    end_time DATETIME COMMENT '结束时间',
    is_anonymous TINYINT NOT NULL DEFAULT 0 COMMENT '是否匿名问卷',
    max_response INT COMMENT '最大答卷数',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (id),
    UNIQUE KEY uk_access_code (access_code),
    KEY idx_creator_status (creator_id, status),
    CONSTRAINT fk_questionnaire_creator FOREIGN KEY (creator_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='问卷表';

-- 问题表
CREATE TABLE question (
    id INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    questionnaire_id INT NOT NULL COMMENT '问卷ID',
    title VARCHAR(500) NOT NULL COMMENT '题目内容',
    type VARCHAR(20) NOT NULL COMMENT '题目类型(SINGLE/MULTIPLE/TEXT/SCORE)',
    required TINYINT NOT NULL DEFAULT 1 COMMENT '是否必答',
    sort INT NOT NULL DEFAULT 0 COMMENT '排序号',
    score_max INT COMMENT '评分最大值',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY idx_questionnaire_sort (questionnaire_id, sort),
    CONSTRAINT fk_question_questionnaire FOREIGN KEY (questionnaire_id) REFERENCES questionnaire (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='问题表';

-- 选项表
CREATE TABLE question_option (
    id INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    question_id INT NOT NULL COMMENT '问题ID',
    content VARCHAR(500) NOT NULL COMMENT '选项内容',
    sort INT NOT NULL DEFAULT 0 COMMENT '排序号',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    KEY idx_question_sort (question_id, sort),
    CONSTRAINT fk_option_question FOREIGN KEY (question_id) REFERENCES question (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='选项表';

-- 答卷表
CREATE TABLE response (
    id INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    questionnaire_id INT NOT NULL COMMENT '问卷ID',
    user_id INT COMMENT '答卷人ID',
    ip_address VARCHAR(50) COMMENT 'IP地址',
    submit_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id),
    KEY idx_questionnaire_user_time (questionnaire_id, user_id, submit_time),
    CONSTRAINT fk_response_questionnaire FOREIGN KEY (questionnaire_id) REFERENCES questionnaire (id),
    CONSTRAINT fk_response_user FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='答卷表';

-- 答案表
CREATE TABLE answer (
    id INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    response_id INT NOT NULL COMMENT '答卷ID',
    question_id INT NOT NULL COMMENT '问题ID',
    option_id INT COMMENT '选项ID(单选题)',
    option_ids VARCHAR(500) COMMENT '选项ID字符串(多选题)',
    text_answer TEXT COMMENT '文本答案',
    score INT COMMENT '评分',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id),
    KEY idx_response_question (response_id, question_id),
    KEY idx_question_option (question_id, option_id),
    CONSTRAINT fk_answer_response FOREIGN KEY (response_id) REFERENCES response (id),
    CONSTRAINT fk_answer_question FOREIGN KEY (question_id) REFERENCES question (id),
    CONSTRAINT fk_answer_option FOREIGN KEY (option_id) REFERENCES question_option (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='答案表';

-- 问卷模板表
CREATE TABLE questionnaire_template (
    id INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    title VARCHAR(200) NOT NULL COMMENT '模板标题',
    description TEXT COMMENT '模板说明',
    creator_id INT NOT NULL COMMENT '创建者ID',
    category VARCHAR(50) NOT NULL COMMENT '模板分类',
    cover_image VARCHAR(255) COMMENT '封面图片URL',
    questions_json TEXT NOT NULL COMMENT '问题和选项内容(JSON)',
    is_public TINYINT NOT NULL DEFAULT 0 COMMENT '是否公开模板',
    use_count INT NOT NULL DEFAULT 0 COMMENT '使用次数',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (id),
    KEY idx_creator_category (creator_id, category),
    KEY idx_public_category (is_public, category, use_count),
    CONSTRAINT fk_template_creator FOREIGN KEY (creator_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='问卷模板表';

-- 公告表
CREATE TABLE notice (
    id INT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    title VARCHAR(200) NOT NULL COMMENT '公告标题',
    content TEXT NOT NULL COMMENT '公告内容',
    type VARCHAR(20) NOT NULL DEFAULT 'NORMAL' COMMENT '公告类型(NORMAL/IMPORTANT/URGENT)',
    publisher_id INT NOT NULL COMMENT '发布人ID',
    status VARCHAR(20) NOT NULL DEFAULT 'DRAFT' COMMENT '状态(DRAFT/PUBLISHED/EXPIRED)',
    is_top TINYINT NOT NULL DEFAULT 0 COMMENT '是否置顶',
    publish_time DATETIME COMMENT '发布时间',
    expire_time DATETIME COMMENT '过期时间',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除',
    PRIMARY KEY (id),
    KEY idx_publisher_status (publisher_id, status, type),
    KEY idx_status_top (status, is_top, publish_time),
    CONSTRAINT fk_notice_publisher FOREIGN KEY (publisher_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公告表';

-- 插入测试数据

-- 插入管理员用户
INSERT INTO user (username, password, nickname, email, role) VALUES
('admin', '123456', '系统管理员', 'admin@example.com', 'ADMIN');

-- 插入普通用户
INSERT INTO user (username, password, nickname, email, role) VALUES
('user1', '123456', '测试用户1', 'user1@example.com', 'USER'),
('user2', '123456', '测试用户2', 'user2@example.com', 'USER');

-- 插入问卷模板
INSERT INTO questionnaire_template (title, description, creator_id, category, questions_json, is_public) VALUES
('满意度调查模板', '用于收集用户满意度反馈', 1, '满意度调查', 
'{
  "questions": [
    {
      "title": "您对我们的服务满意吗？",
      "type": "SCORE",
      "required": true,
      "sort": 1,
      "scoreMax": 5
    },
    {
      "title": "您最喜欢我们的哪些方面？",
      "type": "MULTIPLE",
      "required": true,
      "sort": 2,
      "options": [
        {"content": "服务态度", "sort": 1},
        {"content": "响应速度", "sort": 2},
        {"content": "产品质量", "sort": 3},
        {"content": "价格优惠", "sort": 4}
      ]
    },
    {
      "title": "您的建议",
      "type": "TEXT",
      "required": false,
      "sort": 3
    }
  ]
}', 1);

-- 插入问卷
INSERT INTO questionnaire (title, description, creator_id, status, access_code, access_url, is_anonymous) VALUES
('产品满意度调查', '感谢参与我们的产品满意度调查', 2, 'PUBLISHED', 
'a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6', 'https://your-domain.com/q/a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6', 1);

-- 插入问题
INSERT INTO question (questionnaire_id, title, type, required, sort) VALUES
(1, '您使用我们的产品多久了？', 'SINGLE', 1, 1),
(1, '您最常使用的功能是什么？', 'MULTIPLE', 1, 2),
(1, '您对产品的整体评价', 'SCORE', 1, 3),
(1, '您的建议和意见', 'TEXT', 0, 4);

-- 插入选项
INSERT INTO question_option (question_id, content, sort) VALUES
(1, '1个月以内', 1),
(1, '1-6个月', 2),
(1, '6-12个月', 3),
(1, '1年以上', 4),
(2, '功能A', 1),
(2, '功能B', 2),
(2, '功能C', 3),
(2, '功能D', 4);

-- 插入公告
INSERT INTO notice (title, content, type, publisher_id, status, is_top) VALUES
('系统升级通知', '系统将于本周六凌晨2点进行升级维护', 'IMPORTANT', 1, 'PUBLISHED', 1),
('问卷调查系统上线通知', '欢迎使用新版问卷调查系统', 'NORMAL', 1, 'PUBLISHED', 0); 