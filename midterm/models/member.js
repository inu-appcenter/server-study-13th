const Sequelize = require('sequelize');

module.exports=class Member extends Sequelize.Model{
    static init(sequelize){
        return super.init({
            //id는 따로 설정안해주면 자동으로연결함
            email:{//이메일
                type:Sequelize.STRING(40),
                allowNull:false,
                unique:true,
            },
            password:{//비밀번호
                type:Sequelize.STRING(100),
                allowNull:true,
            },
            age:{//나이
                type:Sequelize.INTEGER,
                allowNull:true,
            },
            name:{//이름
                type:Sequelize.STRING(10),
                allowNull:true,
            },
            status:{//상태
                type:Sequelize.ENUM('사용','삭제'),//회원 상태가 삭제면 조회가 되지 않도록 하기 위함
                allowNull:true,
            }
            
        },{
            sequelize,
            timestamps:true,//생성,수정시간 추가됨 위에서 따로 생성 안해줘도 됨
            underscored:false,
            modelName:'Member',
            tableName:'members',
            paranoid:false,//deltedAt 칼럼 없애줌
            charset:'utf8',
            collate:'utf8_general_ci',
        });
        
    }
    static associate(db){
        db.Member.hasMany(db.Post,{foreignKey:'member',sourceKey:'id'});//게시글과 관계설정
        db.Member.hasMany(db.Comment,{foreignKey:'member',sourceKey:'id'});//댓글과 관계설정
    }
};