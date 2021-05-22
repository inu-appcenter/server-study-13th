const Sequelize = require('sequelize');

module.exports=class Post extends Sequelize.Model{
    static init(sequelize){
        return super.init({
            //id는 따로 설정안해주면 자동으로연결함
            title:{
                type:Sequelize.STRING(20),
                allowNull:true,
            },
            content:{//내용
                type:Sequelize.STRING,
                allowNull:true,
            },
            status:{//게시글 상태
                type:Sequelize.ENUM("사용","삭제"),//삭제인 것은 조회 안되도록
                allowNull:true,
            },
            
            
        },{
            sequelize,
            timestamps:true,//생성,수정시간 추가됨 추가됨 위에서 따로 생성 안해줘도 됨
            underscored:false,
            modelName:'Post',
            tableName:'posts',
            paranoid:false,//deltedAt 칼럼 없애줌
            charset:'utf8',
            collate:'utf8_general_ci',
        });
        
    }
    static associate(db){
        db.Post.belongsTo(db.Member,{foreignKey:'member',targetKey:'id'});//회원과 관계연결
        db.Post.hasMany(db.Comment,{foreignKey:'post',sourceKey:'id'});//댓글과 관계설정
        db.Post.belongsTo(db.Category,{foreignKey:'category',targetKey:'id'});//카테고리와 관계설정
    }
};