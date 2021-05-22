const express = require('express');
const bcrypt = require('bcrypt');

const Member = require('../models/member');
const Post = require('../models/post');
const Comment = require('../models/comment');
const router = express.Router();

//회원가입
router.post('/',async(req,res,next)=>{
    const{email,password,age,name} = req.body;
    try{
        const checking = await Member.findOne({
            where:{
                email//이메일로 가입한 회원 찾아봄
            },
        });
        if(checking){
            return res.json("이미 가입한 이메일 입니다");
            //이미 가입한 이메일이면 응답 메세지로 알림
        }
    
        const hash = await bcrypt.hash(password,12);
        //비밀번호 암호화
    
        const member = await Member.create({
            email,
            password:hash,
            age,
            name,
            status:"사용",
        });
        console.log(member);
        return res.status(200).send();
    }catch(err){
        console.error(err);
        next(err);
    }
    
    
});

//회원 수정
router.patch('/:memberId',async(req,res,next)=>{
    try{
        const result = await Member.update({
            age:req.body.age,
            name:req.body.name,//이름,나이를 body에 보낸 것으로 수정
        },{
            where:{
                id:req.params.memberId,//보낸 아이디 값과 일치하는 회원
            }
        })
        console.log(result);
        res.status(200).send();
    }catch(err){
        console.error(err);
        next(err);
    };
    
    
})

//회원 탈퇴
router.delete('/:memberId',async(req,res,next)=>{
    try{
        const result = await Member.update({
            status:"삭제",
        },{
            where:{
                id:req.params.memberId,
            }
        })
        console.log(result);
        res.status(200).send();
    }catch(err){
        console.error(err);
        next(err);
    }
})

//아이디로 회원조회
router.get('/:memberId',async(req,res,next)=>{
    try{
        const member = await Member.findOne({
            include:{//회원의 게시글 포함
                model:Post,
                where:{status:"사용"}//삭제 상태가 아닌 게시글들만
            },
            where:{
                id:req.params.memberId,
                status:"사용",
            }
        })
        //위의 시퀄라이즈를 이용한 쿼리문을 실행 시에
        //post에 회원이 만든 게시물이 없으면 null값을 반환하는 문제가 생김
        //그래서 아래에 빈 객체를 반환시에 회원만 찾아서 응답하도록 함
        if(!member){
            const justmember = await Member.findOne({
                where:{
                    id:req.params.memberId,
                    status:"사용",
                }
            })
            res.json(justmember);
        }else{
            res.json(member);
        }
        
    }catch(err){
        console.error(err);
        next(err);
    }
})

//게시글 생성
router.post('/:memberId/categories/:categoryId/posts',async(req,res,next)=>{
    const {title,content} = req.body;
    try{
        const post = await Post.create({
            title,
            content,
            status:"사용",
            member:req.params.memberId,
            category:req.params.categoryId,
        })
        console.log(post);
        res.status(200).send();
    }catch(err){
        console.error(err);
        next(err);
    }
})

//댓글 생성
router.post('/:memberId/posts/:postId/comments',async(req,res,next)=>{
    try{
        const comment = await Comment.create({
            content:req.body.content,
            member:req.params.memberId,
            post:req.params.postId,
        })
        console.log(comment);
        res.status(200).send();
    }catch(err){
        console.error(err);
        next(err);
    }
})

module.exports = router;