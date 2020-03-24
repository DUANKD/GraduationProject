import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {BackHandler, Dimensions, Image, Keyboard, TouchableOpacity, View} from "react-native";
import LoginByVerify from "./LoginByVerify";

import CountdownUtil from "../utils/CountdownUtil";
import {zdp} from "../utils/ScreenUtil";
import MyTabView from "../common/MyTabView";
import MyButtonView from "../common/MyButtonView";


const {width, height} = Dimensions.get('window');
export default class Select extends Component{
    static navigationOptions = {
        header: null
    };

    constructor(props) {
        super(props);


        this.state = {
            Supplydemandflag: '',
        };
    }


    componentDidMount() {
        BackHandler.addEventListener("hardwareBackPress", this.onBackPress);
    }

    componentWillUnmount() {
        BackHandler.removeEventListener("hardwareBackPress", this.onBackPress);
        CountdownUtil.stop();
    }


    onBackPress = () => {           // return true   拦截  不让退出
        this.props.navigation.goBack();
        return true;
    };

    render() {
        // this.removeData();

        return (
            <View style={{flex: 1, justifyContent: 'flex-start', alignItems: 'center'}}>
                <MyTabView title={'选择供求方'} isTransparent={true} backgroundColor={'transparent'}
                           globalTitleColor={'white'} barStyle={'light-content'}
                           navigation={this.props.navigation}/>


                <View
                    style={{flex: 1, alignItems: 'center'}}>
                    <MyButtonView
                        modal={1}
                        style={{width: width / 1.3, marginTop: zdp(30)}}
                        title={'供方'}
                        onPress={()=>this.pressSelect("supply")}/>
                    <MyButtonView
                        modal={1}
                        style={{width: width / 1.3, marginTop: zdp(30)}}
                        title={'安装供方'}
                        onPress={this.addSupply}/>
                    <MyButtonView
                        modal={1}
                        style={{width: width / 1.3, marginTop: zdp(30)}}
                        title={'求方'}
                        onPress={()=>this.pressSelect("demand")}/>
                    <MyButtonView
                        modal={1}
                        style={{width: width / 1.3, marginTop: zdp(30)}}
                        title={'安装求方'}
                        onPress={this.addDemand}/>
                </View>
            </View>
        );
    }
    pressSelect = (Supplydemandflag) => {
        this.props.navigation.navigate('LoginByVerify',{ Supplydemandflag: Supplydemandflag});
    }
    addSupply = () => {
        alert("添加供方");
    }
    addDemand = () => {
        alert("添加求方");
    }
}
Select.propTypes = {
    Supplydemandflag: PropTypes.string
};