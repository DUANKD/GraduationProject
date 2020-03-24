import React from 'react';
import {StackNavigator} from 'react-navigation';
import {TabNav} from "./RootPage";

import LoginScreen from "../userCommon/login";
import LoginByVerify from "../userCommon/LoginByVerify";
import Select from "../userCommon/Select";
import PayScreen from "../userCommon/Pay/Pay";
import RegisteSupply from "../supply/registe";
import RegisteDemand from "./registe";
import RegisterSuccess from  "../userCommon/RegisterSuccess";


import ForgetPsw from "../common/ForgetPsw";


const App = StackNavigator({
        Pay: {screen: PayScreen},//支付页
        Select: {screen: Select},//选择供求
        Login: {screen: LoginScreen}, // 登录页
        LoginByVerify: {screen: LoginByVerify}, // 短信登录
        ForgetPsw: {screen: ForgetPsw}, // 忘记密码
        RegisteSupply: {screen: RegisteSupply}, // 注册供方
        RegisteDemand: {screen: RegisteDemand}, // 注册求方
        RegisterSuccess: {screen: RegisterSuccess}, // 注册成功
        Main: {
            screen: TabNav,
            navigationOptions: ({navigation}) => ({
                header: null
            })
        }
    },
    {
        initialRouteName: 'Main',
        headerMode: 'screen'
    });

export default App;
