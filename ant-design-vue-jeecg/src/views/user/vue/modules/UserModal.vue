<template>
  <j-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    switchFullscreen
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="用户ID" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['userid', validatorRules.userid]" placeholder="请输入用户ID" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="openid" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['openid']" placeholder="请输入openid"></a-input>
        </a-form-item>
        <a-form-item label="unionid" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['unionid']" placeholder="请输入unionid"></a-input>
        </a-form-item>
        <a-form-item label="昵称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['username']" placeholder="请输入昵称"></a-input>
        </a-form-item>
        <a-form-item label="头像" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['avatar']" placeholder="请输入头像"></a-input>
        </a-form-item>
        <a-form-item label="挑战计数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['challengecount']" placeholder="请输入挑战计数" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="PK计数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['pkcount']" placeholder="请输入PK计数" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="抖音计数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['shortcount']" placeholder="请输入抖音计数" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="余额单位：分" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['balance']" placeholder="请输入余额单位：分" style="width: 100%"/>
        </a-form-item>

      </a-form>
    </a-spin>
  </j-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'


  export default {
    name: "UserModal",
    components: { 
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
          userid: {
            rules: [
              { required: true, message: '请输入用户ID!'},
            ]
          },
        },
        url: {
          add: "/user/user/add",
          edit: "/user/user/edit",
        }
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'userid','openid','unionid','username','avatar','challengecount','pkcount','shortcount','balance'))
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }
         
        })
      },
      handleCancel () {
        this.close()
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'userid','openid','unionid','username','avatar','challengecount','pkcount','shortcount','balance'))
      },

      
    }
  }
</script>