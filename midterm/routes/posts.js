const express = require('express');

const Post = require('../models/post');
const Comment = require('../models/comment');

const router = express.Router();

//게시글 수정
router.patch('/:postId',async(req,res,next)=>{
    try{
        const result = await Post.update({
            title:req.body.title,
            content:req.body.content,
        },{
            where:{
                id:req.params.postId,
            }
        })
        console.log(result);
        res.status(200).send();
    }catch(err){
        console.error(err);
        next(err);
    }
})

//게시글 삭제 -> 상태만 변경
router.delete('/:postId',async(req,res,next)=>{
    try{
        const result = await Post.update({
            status:"삭제",
        },{
            where:{
                id:req.params.postId,
            }
        });
        res.status(200).send();
    }catch(err){
        console.error(err);
        next(err);
    }
})

//게시글 아이디 조회
router.get('/:postId',async(req,res,next)=>{
    try{
        const post = await Post.findOne({
            include:{
                model:Comment,
            },
            where:{
                id:req.params.postId,
            },
        })
        res.json(post);
    }catch(err){
        console.error(err);
        next(err);
    }
})

module.exports = router;