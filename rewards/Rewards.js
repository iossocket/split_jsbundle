import React from 'react'
import { StyleSheet, View, Text, StatusBar, Button, NativeModules } from 'react-native'
import Header from '../src/components/Header'

export default Rewards = () => {
  return (
    <View style={styles.fill}>
      <StatusBar translucent={true} barStyle={'dark-content'} backgroundColor={'transparent'} />
      <Header title="Register" fullScreen left={
        <Button title="点击注册" onPress={() => NativeModules.RNNavigation.goBack()}/>
      }/>
      <Text style={styles.text}>Rewards</Text>
    </View>
  )
}

const styles = StyleSheet.create({
  fill: {
    flex: 1
  },
  text: {
    marginTop: 32,
    fontSize: 20,
    fontWeight: '500',
    color: '#437dff',
    textAlign: 'center'
  }
})