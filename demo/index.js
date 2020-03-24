/**
 * @format
 */

import {AppRegistry} from 'react-native';
import App from './App';
import Demand from './src/demand/root'
import {name as appName} from './app.json';

//AppRegistry.registerComponent(appName, () => App);
AppRegistry.registerComponent(appName, () => Demand);

