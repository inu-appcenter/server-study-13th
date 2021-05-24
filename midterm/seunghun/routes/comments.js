const express = require('express');

const Comment = require('../models/comment');

const router = express.Router();

//댓글 수정
router.patch('/:commentId',async(req,res,next)=>{
    try{
        const result = Comment.update({
            content:req.body.content,
        },{
            where:{
                id:req.params.commentId,
            }
        })
        console.log(result);
        res.status(200).send();
    }catch(err){
        console.error(err);
        next(err);
    }
})


//댓글 삭제
router.delete('/:commentId',async(req,res,next)=>{
    try{
        const result = Comment.destroy({
            where:{
                id:req.params.commentId,
            }
        })
        console.log(result);
        res.status(200).send();
    }catch(err){
        console.error(err);
        next(err);
    }
})

module.exports = router;