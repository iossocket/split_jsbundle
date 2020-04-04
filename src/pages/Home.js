import React from 'react'
import { StyleSheet, View, ImageBackground, Button, StatusBar, NativeModules } from 'react-native'

import Header from '../components/Header'

export default Home = (props) => {
  return (
    <View style={styles.fill}>
      <StatusBar translucent={true} barStyle={'light-content'} backgroundColor={'transparent'} />
      <ImageBackground style={styles.bg} source={require('../assets/imgs/bg.png')}>
        <Header title="Home" fullScreen />
      </ImageBackground>
      <View style={styles.buttonWrapper}>
        <Button
          title="Rewards"
          onPress={() => {
            NativeModules.RNNavigation.navigateTo("rewards");
          }}
          color="#437dff"
        />
      </View>
    </View>
  )
}
// export default class Home extends React.PureComponent {
//   static navigationOptions = {
//     title: '主页'
//   }

//   render() {
//   }
// }

const styles = StyleSheet.create({
  fill: {
    flex: 1
  },
  bg: {
    height: 234,
  },
  text: {
    fontSize: 20,
    fontWeight: '500',
    color: '#437dff',
    textAlign: 'center'
  },
  buttonWrapper: {
    padding: 16
  }
})