define({ "api": [
  {
    "type": "get",
    "url": "jmh/rms/getNewUserCount",
    "title": "今日新增注册军人数",
    "version": "1.0.0",
    "group": "RMS",
    "name": "getNewUserCount",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回数量</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/RmsController.java",
    "groupTitle": "RMS"
  },
  {
    "type": "get",
    "url": "jmh/rms/getUserCount",
    "title": "累计注册军人数",
    "version": "1.0.0",
    "group": "RMS",
    "name": "getUserCount",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回数量</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/RmsController.java",
    "groupTitle": "RMS"
  },
  {
    "type": "post",
    "url": "jmh/rms/login",
    "title": "RMS登录",
    "version": "1.0.0",
    "group": "RMS",
    "name": "login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "loginName",
            "description": "<p>登录姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "loginPwd",
            "description": "<p>登录密码</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回提示文本</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/RmsController.java",
    "groupTitle": "RMS"
  },
  {
    "type": "get",
    "url": "jmh/rms/loginOut",
    "title": "登出",
    "version": "1.0.0",
    "group": "RMS",
    "name": "loginOut",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回数量</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态3登出</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/RmsController.java",
    "groupTitle": "RMS"
  },
  {
    "type": "post",
    "url": "jmh/qiniu/upload",
    "title": "上传图片",
    "version": "1.0.0",
    "group": "qiniu",
    "name": "upload",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "path",
            "description": "<p>上传文件（路径）</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回存储url/错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表成功 /1代表有错误状态</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/QiniuController.java",
    "groupTitle": "qiniu"
  },
  {
    "type": "post",
    "url": "jmh/qiniu/uploadImg",
    "title": "上传图片",
    "version": "1.0.0",
    "group": "qiniu",
    "name": "uploadImg",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "MultipartFile",
            "optional": false,
            "field": "file",
            "description": "<p>上传文件（文件类型）</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回存储url/错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表成功 /1代表有错误状态</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/QiniuController.java",
    "groupTitle": "qiniu"
  },
  {
    "type": "get",
    "url": "jmh/rms/getAllCompany",
    "title": "获取所有注册企业信息",
    "version": "1.0.0",
    "group": "rms",
    "name": "getAllCompany",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "optional": false,
            "field": "currentPage",
            "description": "<p>页数</p>"
          },
          {
            "group": "Parameter",
            "optional": false,
            "field": "pageSize",
            "description": "<p>分页数</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "total",
            "description": "<p>：总条数</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "companyRoleId",
            "description": "<p>公司编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "companyContacts",
            "description": "<p>联系人</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>电话</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "companyName",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "account",
            "description": "<p>账号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>密码</p>"
          },
          {
            "group": "Success 200",
            "type": "date",
            "optional": false,
            "field": "joinTime",
            "description": "<p>创建时间</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/RmsController.java",
    "groupTitle": "rms"
  },
  {
    "type": "get",
    "url": "jmh/rms/getAllUser",
    "title": "获取所有注册军人信息",
    "version": "1.0.0",
    "group": "rms",
    "name": "getAllUser",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "optional": false,
            "field": "currentPage",
            "description": "<p>页数</p>"
          },
          {
            "group": "Parameter",
            "optional": false,
            "field": "pageSize",
            "description": "<p>分页数</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "total",
            "description": "<p>总条数</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "id",
            "description": "<p>ID</p>"
          },
          {
            "group": "Success 200",
            "type": "date",
            "optional": false,
            "field": "joinTime",
            "description": "<p>提交时间</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "officerImage",
            "description": "<p>军人证</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "officerNumber",
            "description": "<p>军人编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>电话</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "resident",
            "description": "<p>户籍所在地</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "socialId",
            "description": "<p>身份证编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userId",
            "description": "<p>用户编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "userName",
            "description": "<p>用户姓名</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/RmsController.java",
    "groupTitle": "rms"
  },
  {
    "type": "post",
    "url": "jmh/rms/newCompanyRole",
    "title": "录入企业账号信息",
    "version": "1.0.0",
    "group": "rms",
    "name": "newCompanyRole",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "companyName",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "companyContacts",
            "description": "<p>联系人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>联系号码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "account",
            "description": "<p>企业账号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>密码</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回提示文本</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/RmsController.java",
    "groupTitle": "rms"
  },
  {
    "type": "post",
    "url": "jmh/rms/updateCompanyRole",
    "title": "更新公司账号信息",
    "version": "1.0.0",
    "group": "rms",
    "name": "updateCompanyRole",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "roleId",
            "description": "<p>企业ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "companyName",
            "description": "<p>企业名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "companyContacts",
            "description": "<p>联系人</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>联系号码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "account",
            "description": "<p>企业账号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>密码</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回执行错误</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/RmsController.java",
    "groupTitle": "rms"
  },
  {
    "type": "get",
    "url": "jmh/weChat/bindPhone",
    "title": "绑定手机号码",
    "version": "1.0.0",
    "group": "weChat",
    "name": "bindPhone",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "openId",
            "description": "<p>微信openId</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "telephone",
            "description": "<p>联系电话</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>成功返回phone，失败返回错误</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/WeChatController.java",
    "groupTitle": "weChat"
  },
  {
    "type": "get",
    "url": "jmh/weChat/check?checkCode=666888",
    "title": "验证码验证",
    "version": "1.0.0",
    "group": "weChat",
    "name": "check",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "checkCode",
            "description": "<p>6位验证码</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回执行错误</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/WeChatController.java",
    "groupTitle": "weChat"
  },
  {
    "type": "get",
    "url": "jmh/weChat/deleteJobProvidedInfo",
    "title": "删除职位信息",
    "version": "1.0.0",
    "group": "weChat",
    "name": "deleteJobProvidedInfo",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "jobId",
            "description": "<p>职位编号</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回执行错误</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/WeChatController.java",
    "groupTitle": "weChat"
  },
  {
    "type": "get",
    "url": "jmh/weChat/getAllJobPvdByCompId",
    "title": "获取本公司所有职位信息",
    "version": "1.0.0",
    "group": "weChat",
    "name": "getAllJobPvdByCompId",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "optional": false,
            "field": "companyId",
            "description": "<p>页数</p>"
          },
          {
            "group": "Parameter",
            "optional": false,
            "field": "currentPage",
            "description": "<p>页数</p>"
          },
          {
            "group": "Parameter",
            "optional": false,
            "field": "pageSize",
            "description": "<p>分页数</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "total",
            "description": "<p>总数量</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "jobId",
            "description": "<p>职位编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "jobName",
            "description": "<p>职位名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "salaryRange",
            "description": "<p>薪资范围</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "location",
            "description": "<p>工作地点</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "companyId",
            "description": "<p>公司编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "companyName",
            "description": "<p>公司名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "jobDesc",
            "description": "<p>职位描述</p>"
          },
          {
            "group": "Success 200",
            "type": "Char",
            "optional": false,
            "field": "eduKey",
            "description": "<p>学历类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "eduValue",
            "description": "<p>学历名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Char",
            "optional": false,
            "field": "industryKey",
            "description": "<p>行业类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "industryValue",
            "description": "<p>行业名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Char",
            "optional": false,
            "field": "compSizeKey",
            "description": "<p>公司规模类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "compSizeValue",
            "description": "<p>公司规模名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Char",
            "optional": false,
            "field": "jobExpKey",
            "description": "<p>工作经验类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "jobExpValue",
            "description": "<p>工作经验名称</p>"
          },
          {
            "group": "Success 200",
            "type": "date",
            "optional": false,
            "field": "updateTime",
            "description": "<p>创建/修改时间</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "updateBy",
            "description": "<p>创建人/修改人</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/WeChatController.java",
    "groupTitle": "weChat"
  },
  {
    "type": "get",
    "url": "jmh/weChat/getAllJobPvdInfo",
    "title": "获取所有职位信息",
    "version": "1.0.0",
    "group": "weChat",
    "name": "getAllJobPvdInfo",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "optional": false,
            "field": "currentPage",
            "description": "<p>页数</p>"
          },
          {
            "group": "Parameter",
            "optional": false,
            "field": "pageSize",
            "description": "<p>分页数</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "total",
            "description": "<p>总数量</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "jobId",
            "description": "<p>职位编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "jobName",
            "description": "<p>职位名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "salaryRange",
            "description": "<p>薪资范围</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "location",
            "description": "<p>工作地点</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "companyId",
            "description": "<p>公司编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "companyName",
            "description": "<p>公司名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "jobDesc",
            "description": "<p>职位描述</p>"
          },
          {
            "group": "Success 200",
            "type": "Char",
            "optional": false,
            "field": "eduKey",
            "description": "<p>学历类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "eduValue",
            "description": "<p>学历名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Char",
            "optional": false,
            "field": "industryKey",
            "description": "<p>行业类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "industryValue",
            "description": "<p>行业名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Char",
            "optional": false,
            "field": "compSizeKey",
            "description": "<p>公司规模类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "compSizeValue",
            "description": "<p>公司规模名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Char",
            "optional": false,
            "field": "jobExpKey",
            "description": "<p>工作经验类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "jobExpValue",
            "description": "<p>工作经验名称</p>"
          },
          {
            "group": "Success 200",
            "type": "date",
            "optional": false,
            "field": "updateTime",
            "description": "<p>创建/修改时间</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "updateBy",
            "description": "<p>创建人/修改人</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/WeChatController.java",
    "groupTitle": "weChat"
  },
  {
    "type": "get",
    "url": "jmh/weChat/getAllJobWantedInfo",
    "title": "获取所有求职信息",
    "version": "1.0.0",
    "group": "weChat",
    "name": "getAllJobWantedInfo",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "optional": false,
            "field": "currentPage",
            "description": "<p>页数</p>"
          },
          {
            "group": "Parameter",
            "optional": false,
            "field": "pageSize",
            "description": "<p>分页数</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "total",
            "description": "<p>总数量</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "wantedId",
            "description": "<p>求职编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "wantedName",
            "description": "<p>求职者姓名</p>"
          },
          {
            "group": "Success 200",
            "type": "char",
            "optional": false,
            "field": "male",
            "description": "<p>求职者性别</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "age",
            "description": "<p>求职者年龄</p>"
          },
          {
            "group": "Success 200",
            "type": "char",
            "optional": false,
            "field": "eduKey",
            "description": "<p>学历类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "serveExp",
            "description": "<p>服役经历</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "wantedPlace",
            "description": "<p>期望工作地点</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "wantedJob1",
            "description": "<p>期望职位1</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "wantedJob2",
            "description": "<p>期望职位2</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "advantage1",
            "description": "<p>优势1</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "advantage2",
            "description": "<p>优势2</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "updateBy",
            "description": "<p>创建/修改人</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/WeChatController.java",
    "groupTitle": "weChat"
  },
  {
    "type": "get",
    "url": "jmh/weChat/getJobPvdInfoByJobId",
    "title": "获取职位详情",
    "version": "1.0.0",
    "group": "weChat",
    "name": "getJobProvidedInfo",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "optional": false,
            "field": "jobId",
            "description": "<p>职位编号</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "jobId",
            "description": "<p>职位编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "jobName",
            "description": "<p>职位名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "salaryRange",
            "description": "<p>薪资范围</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "location",
            "description": "<p>工作地点</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "companyId",
            "description": "<p>公司编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "companyName",
            "description": "<p>公司名称</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "jobDesc",
            "description": "<p>职位描述</p>"
          },
          {
            "group": "Success 200",
            "type": "Char",
            "optional": false,
            "field": "eduKey",
            "description": "<p>学历类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "eduValue",
            "description": "<p>学历名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Char",
            "optional": false,
            "field": "industryKey",
            "description": "<p>行业类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "industryValue",
            "description": "<p>行业名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Char",
            "optional": false,
            "field": "compSizeKey",
            "description": "<p>公司规模类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "compSizeValue",
            "description": "<p>公司规模名称</p>"
          },
          {
            "group": "Success 200",
            "type": "Char",
            "optional": false,
            "field": "jobExpKey",
            "description": "<p>工作经验类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "jobExpValue",
            "description": "<p>工作经验名称</p>"
          },
          {
            "group": "Success 200",
            "type": "date",
            "optional": false,
            "field": "updateTime",
            "description": "<p>创建/修改时间</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "updateBy",
            "description": "<p>创建人/修改人</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/WeChatController.java",
    "groupTitle": "weChat"
  },
  {
    "type": "get",
    "url": "jmh/weChat/getJobWantedById",
    "title": "获取求职详情",
    "version": "1.0.0",
    "group": "weChat",
    "name": "getJobWantedById",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "optional": false,
            "field": "wantedId",
            "description": "<p>求职编号</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "total",
            "description": "<p>总数量</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "wantedId",
            "description": "<p>求职编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "wantedName",
            "description": "<p>求职者姓名</p>"
          },
          {
            "group": "Success 200",
            "type": "char",
            "optional": false,
            "field": "male",
            "description": "<p>求职者性别</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "age",
            "description": "<p>求职者年龄</p>"
          },
          {
            "group": "Success 200",
            "type": "char",
            "optional": false,
            "field": "eduKey",
            "description": "<p>学历类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "serveExp",
            "description": "<p>服役经历</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "wantedPlace",
            "description": "<p>期望工作地点</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "wantedJob1",
            "description": "<p>期望职位1</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "wantedJob2",
            "description": "<p>期望职位2</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "advantage1",
            "description": "<p>优势1</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "advantage2",
            "description": "<p>优势2</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "updateBy",
            "description": "<p>创建/修改人</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/WeChatController.java",
    "groupTitle": "weChat"
  },
  {
    "type": "get",
    "url": "jmh/weChat/getPhone",
    "title": "累计注册军人数",
    "version": "1.0.0",
    "group": "weChat",
    "name": "getPhone",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "jmhsId",
            "description": "<p>军人编号</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>成功返回phone，失败返回错误</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/WeChatController.java",
    "groupTitle": "weChat"
  },
  {
    "type": "get",
    "url": "jmh/weChat/getWechatUserInfo",
    "title": "获取微信用户基本信息",
    "version": "1.0.0",
    "group": "weChat",
    "name": "getUserInfo",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回错误消息</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0/1 代表无/有错误状态</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "openid",
            "description": "<p>微信用户openId</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "nickname",
            "description": "<p>微信用户openId</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "sex",
            "description": "<p>性别 1男2女</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "language",
            "description": "<p>语言</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "city",
            "description": "<p>市</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "province",
            "description": "<p>省</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "country",
            "description": "<p>国家</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "headimgurl",
            "description": "<p>头像路径</p>"
          },
          {
            "group": "Success 200",
            "type": "date",
            "optional": false,
            "field": "subscribe_time",
            "description": ""
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/WeChatController.java",
    "groupTitle": "weChat"
  },
  {
    "type": "post",
    "url": "jmh/weChat/login",
    "title": "公众号登录",
    "version": "1.0.0",
    "group": "weChat",
    "name": "login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "loginName",
            "description": "<p>登录姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "loginPwd",
            "description": "<p>登录密码</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回错误消息</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0/1 代表无/有错误状态</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/WeChatController.java",
    "groupTitle": "weChat"
  },
  {
    "type": "get",
    "url": "jmh/weChat/loginOut",
    "title": "公众号退出登录",
    "version": "1.0.0",
    "group": "weChat",
    "name": "loginOut",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回错误消息</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>3登出成功</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/WeChatController.java",
    "groupTitle": "weChat"
  },
  {
    "type": "get",
    "url": "jmh/weChat/newJobProvidedInfo",
    "title": "创建职位信息",
    "version": "1.0.0",
    "group": "weChat",
    "name": "newJobProvidedInfo",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "jobName",
            "description": "<p>职位名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "salaryRange",
            "description": "<p>薪资范围</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "location",
            "description": "<p>工作地点</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "companyId",
            "description": "<p>公司编号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "companyName",
            "description": "<p>公司名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "jobDesc",
            "description": "<p>职位描述</p>"
          },
          {
            "group": "Parameter",
            "type": "Char",
            "optional": false,
            "field": "eduKey",
            "description": "<p>学历类型</p>"
          },
          {
            "group": "Parameter",
            "type": "Char",
            "optional": false,
            "field": "industryKey",
            "description": "<p>行业类型</p>"
          },
          {
            "group": "Parameter",
            "type": "Char",
            "optional": false,
            "field": "compSizeKey",
            "description": "<p>公司规模类型</p>"
          },
          {
            "group": "Parameter",
            "type": "Char",
            "optional": false,
            "field": "jobExpKey",
            "description": "<p>工作经验类型</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "updateBy",
            "description": "<p>创建人/修改人</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回提示文本</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/WeChatController.java",
    "groupTitle": "weChat"
  },
  {
    "type": "get",
    "url": "jmh/weChat/newJobWantedInfo",
    "title": "创建求职信息",
    "version": "1.0.0",
    "group": "weChat",
    "name": "newJobWantedInfo",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "wantedName",
            "description": "<p>求职者姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "char",
            "optional": false,
            "field": "male",
            "description": "<p>求职者性别M/F</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "age",
            "description": "<p>求职者年龄</p>"
          },
          {
            "group": "Parameter",
            "type": "char",
            "optional": false,
            "field": "edukey",
            "description": "<p>学历类型</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "serveExp",
            "description": "<p>服役经历</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "wantedPlace",
            "description": "<p>期望工作地点</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "wantedJob1",
            "description": "<p>期望职位1</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "wantedJob2",
            "description": "<p>期望职位2</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "advantage1",
            "description": "<p>优势1</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "advantage2",
            "description": "<p>优势2</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "updateBy",
            "description": "<p>创建/修改人</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回提示文本</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "wantedId",
            "description": "<p>求职编号</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "wantedName",
            "description": "<p>求职者姓名</p>"
          },
          {
            "group": "Success 200",
            "type": "char",
            "optional": false,
            "field": "male",
            "description": "<p>求职者性别</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "age",
            "description": "<p>求职者年龄</p>"
          },
          {
            "group": "Success 200",
            "type": "char",
            "optional": false,
            "field": "eduKey",
            "description": "<p>学历类型</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "serveExp",
            "description": "<p>服役经历</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "wantedPlace",
            "description": "<p>期望工作地点</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "wantedJob1",
            "description": "<p>期望职位1</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "wantedJob2",
            "description": "<p>期望职位2</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "advantage1",
            "description": "<p>优势1</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "advantage2",
            "description": "<p>优势2</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "updateBy",
            "description": "<p>创建/修改人</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/WeChatController.java",
    "groupTitle": "weChat"
  },
  {
    "type": "get",
    "url": "jmh/weChat/sms?telephone=15990111539",
    "title": "验证码发送",
    "version": "1.0.0",
    "group": "weChat",
    "name": "sms",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "telephone",
            "description": "<p>手机号码</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回执行错误</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "telephone",
            "description": "<p>手机号码</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "checkCode",
            "description": "<p>6位验证码(5分钟失效)</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/WeChatController.java",
    "groupTitle": "weChat"
  },
  {
    "type": "get",
    "url": "jmh/weChat/updateJobProvidedInfo",
    "title": "编辑职位信息",
    "version": "1.0.0",
    "group": "weChat",
    "name": "updateJobProvidedInfo",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "jobId",
            "description": "<p>职位编号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "jobName",
            "description": "<p>职位名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "salaryRange",
            "description": "<p>薪资范围</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "location",
            "description": "<p>工作地点</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "companyId",
            "description": "<p>公司编号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "companyName",
            "description": "<p>公司名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "jobDesc",
            "description": "<p>职位描述</p>"
          },
          {
            "group": "Parameter",
            "type": "Char",
            "optional": false,
            "field": "eduKey",
            "description": "<p>学历类型</p>"
          },
          {
            "group": "Parameter",
            "type": "Char",
            "optional": false,
            "field": "industryKey",
            "description": "<p>行业类型</p>"
          },
          {
            "group": "Parameter",
            "type": "Char",
            "optional": false,
            "field": "compSizeKey",
            "description": "<p>公司规模类型</p>"
          },
          {
            "group": "Parameter",
            "type": "Char",
            "optional": false,
            "field": "jobExpKey",
            "description": "<p>工作经验类型</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "updateBy",
            "description": "<p>创建人/修改人</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回执行错误</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/WeChatController.java",
    "groupTitle": "weChat"
  },
  {
    "type": "get",
    "url": "jmh/website/deleteVisitor",
    "title": "删除游客信息",
    "version": "1.0.0",
    "group": "website",
    "name": "deleteVisitor",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>注意: 非页面序号</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回执行错误</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response: { \"message\"",
          "content": ":\"\", \"statusCode\": 0 }",
          "type": "json"
        }
      ]
    },
    "filename": "src/com/jmh/controller/WebsiteController.java",
    "groupTitle": "website"
  },
  {
    "type": "get",
    "url": "jmh/website/getAllVisitor",
    "title": "获取所有登记游客信息",
    "version": "1.0.0",
    "group": "website",
    "name": "getAllVisitor",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "optional": false,
            "field": "currentPage",
            "description": "<p>页数</p>"
          },
          {
            "group": "Parameter",
            "optional": false,
            "field": "pageSize",
            "description": "<p>分页数</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "total",
            "description": "<p>总数量</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "visitorName",
            "description": "<p>联系人</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>联系电话</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "visitorTypeName",
            "description": "<p>身份</p>"
          },
          {
            "group": "Success 200",
            "type": "date",
            "optional": false,
            "field": "sumbitTime",
            "description": "<p>提交时间</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "visitorStatus",
            "description": "<p>状态</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/WebsiteController.java",
    "groupTitle": "website"
  },
  {
    "type": "get",
    "url": "jmh/website/newVisitor",
    "title": "新建游客信息（免费加盟）",
    "version": "1.0.0",
    "group": "website",
    "name": "newVisitor",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "visitorName",
            "description": "<p>游客姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>联系方式</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "visitorStatus",
            "description": "<p>默认为空</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "visitorType",
            "description": "<p>企业/导师</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回提示文本</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/WebsiteController.java",
    "groupTitle": "website"
  },
  {
    "type": "post",
    "url": "jmh/website/updateVisitor",
    "title": "更新游客信息",
    "version": "1.0.0",
    "group": "website",
    "name": "updateVisitor",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>注意: 非页面序号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "visitorName",
            "description": "<p>游客姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>联系方式</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "visitorStatus",
            "description": "<p>默认为空</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "visitorType",
            "description": "<p>企业/导师</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>返回执行错误</p>"
          },
          {
            "group": "Success 200",
            "type": "int",
            "optional": false,
            "field": "statusCode",
            "description": "<p>0 代表无错误 1代表有错误状态</p>"
          }
        ]
      }
    },
    "filename": "src/com/jmh/controller/WebsiteController.java",
    "groupTitle": "website"
  }
] });
