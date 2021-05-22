const express = require('express');
const cookieparser = require('cookie-parser');
const morgan = require('morgan');
const path = require('path');
const session = require('express-session');
const dotenv = require('dotenv');
const passport = require('passport');
const nunjucks = require('nunjucks');

dotenv.config();

//라우터 연결
const memberRouter = require('./routes/members');
const postRouter = require('./routes/posts');
const categoryRouter = require('./routes/categories');
const commentRouter = require('./routes/comments');

const {sequelize} = require('./models');//db 모델 서버 연결하기 위해서 사용

//const passportConfig = require('./passport');

const app = express();
//passportConfig();//패스포트 설정
app.set('port',process.env.PORT || 8001);
app.set('view engine','html');
nunjucks.configure('views',{
    express:app,
    watch:true,
});

sequelize.sync({force:false})
    .then(()=>{
        console.log('데이터 베이스 연결 성공');
    })
    .catch((err)=>{
        console.error(err);
    });
//index.js에서 db를 불러와서 sync메서드를 사용해 서버 실행 시 MYSQL과 연동되는 것
//force:false 옵션을 true로 설정하면 서버 실행 시마다 테이블을 재생성함
//테이블 잘못 만든 경우에 true로 설정함

app.use(morgan('dev'));
//app.use(express.static(path.join(__dirname,'public')));
//static 미들웨어는 정적인 파일들을 제공하는 라우터 역할
//기본제공 되므로 express객체 안에서 꺼내서 사용
//app.use('요청경로',express.static('실제경로'));

app.use(express.json());
app.use(express.urlencoded({extended:false}));
app.use(cookieparser(process.env.COOKIE_SECRET));
app.use(session({
    resave:false,
    saveUninitialized:false,
    secret:process.env.COOKIE_SECRET,
    cookie:{
        httpOnly:true,
        secure:false,
    },
}));

//요청(req객체)에 passport 설정을 심음
app.use(passport.initialize());

//req.session객체에 passport 정보를 저장함
app.use(passport.session());

//라우터 분기
app.use('/members',memberRouter);
app.use('/posts',postRouter);
app.use('/categories',categoryRouter);
app.use('/comments',commentRouter);

//404에러
app.use((req,res,next)=>{
    const error=new Error(`${req.method} ${req.url}라우터가 없습니다.`);
    error.status=404;
    next(error);
});

//500에러
app.use((err,req,res,next)=>{
    res.locals.message=err.message;
    res.locals.error=process.env.NODE_ENV !=='production'? err:{};
    res.status(err.status||500);
    res.render('error');
});

app.listen(app.get('port'),()=>{
    console.log(app.get('port'),'번 포트에서 대기 중');
});