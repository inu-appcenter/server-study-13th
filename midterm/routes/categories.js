const express = require('express');
const Sequelize = require('sequelize');

const Category = require('../models/category');
const Post = require('../models/post');
const Comment = require('../models/comment');
const { sequelize } = require('../models');

const router = express.Router();

//카테고리 생성
router.post('/',async(req,res,next)=>{
    const {content} = req.body;
    try{
        const checking = await Category.findOne({//중복체크를 위해 해당 콘텐츠 있는지 확인
            where:{
                content,
            }
        })
        if(checking){//있는지 확인함
            return res.json("이미 생성된 카테고리 입니다");//있으면 리턴하고 종료
        }

        const category = await Category.create({
            content,
            status:"사용",
        })
        console.log(category);
        res.status(200).send();
    }catch(err){
        console.error(err);
        next(err);
    }
})

//카테고리 삭제
router.delete('/:categoryId',async(req,res,next)=>{
    try{
        const result = await Category.update({
            status:"삭제",
        },{
            where:{
                id:req.params.categoryId,
            }
        });
        res.status(200).send();
    }catch(err){
        console.error(err);
        next(err);
    }
})

//카테고리 리스트 조회
router.get('/',async(req,res,next)=>{
    try{
        const category = await Category.findAll({
            where:{//삭제가아닌 -> 사용인 것만 조회
                status:"사용",
            }
        });
        console.log(category);
        res.json(category);
    }catch(err){
        console.error(err);
        next(err);
    }
})

//카테고리 아이디로 게시글 리스트 조회
router.get('/:categoryId/posts',async(req,res,next)=>{
    try{
        //시퀄라이즈로 만들기 복잡해서 쿼리문 그대로 수행함
        //기존 쿼리문 : select posts.* , count(*) as postCount from posts, comments where posts.status="사용" and posts.id = comments.post and posts.category = ${req.params.categoryId} group by posts.id;
        //해당 쿼리문에 posts.id = comments.post 에서 댓글이 없는 게시물이면 해당 구문에서 일치하는 값이 없으므로 Null을 반환하는 오류 발생
        //그래서 셀렉문 안에 셀렉문을 넣어서 먼저 posts.id = comments.post를 적용하여 갯수를 센 다음 게시물을 찾게 하고 그룹화 함
        const [post,metadata] = await sequelize.query(`select posts.* , (select count(*) from comments where posts.id = comments. post)  as commentCount from posts, comments where posts.status="사용" and posts.category = ${req.params.categoryId} group by posts.id;`)
        
        console.log(post);
        res.json(post);
    }catch(err){
        console.error(err);
        next(err);
    }
})

module.exports = router;