const Sequelize = require('sequelize');

module.exports=class Comment extends Sequelize.Model{
    static init(sequelize){
        return super.init({
            //id는 따로 설정안해주면 자동으로연결함
            content:{
                type:Sequelize.STRING,
                allowNull:true, 
            },
            
            
        },{
            sequelize,
            timestamps:true,//생성,수정시간 추가됨 추가됨 위에서 따로 생성 안해줘도 됨
            underscored:false,
            modelName:'Comment',
            tableName:'comments',
            paranoid:false,//deltedAt 칼럼 없애줌
            charset:'utf8',
            collate:'utf8_general_ci',
        });
        
    }
    static associate(db){
        db.Comment.belongsTo(db.Member,{foreignKey:'member',targetKey:'id'});//회원과 관계설정
        db.Comment.belongsTo(db.Post,{foreignKey:'post',targetKey:'id'});//게시글과 관계설정
    }
};