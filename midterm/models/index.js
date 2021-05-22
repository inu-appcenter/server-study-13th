const Sequelize = require('sequelize');
const env = process.env.NODE_ENV || 'development';
const config = require('../config/config')[env];

const Member = require('./member');
const Comment = require('./comment');
const Post = require('./post');
const Category = require('./category');

const db ={};

const sequelize = new Sequelize(config.databse, config.username, config.password,config);
db.sequelize = sequelize;

//db객체를 이용하여 아래 모델들에 접근 가능하게 함
db.Member = Member;
db.Comment =Comment;
db.Post =Post;
db.Category = Category;

//각 모델의 static.init메서드를 호출 ->init 실행이 되어야 테이블이 모델로 연결됨
Member.init(sequelize);
Comment.init(sequelize);
Post.init(sequelize);
Category.init(sequelize);

//associate로 설정한 관계 연결함
Member.associate(db);
Comment.associate(db);
Post.associate(db);
Category.associate(db);

module.exports = db;