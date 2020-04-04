import React from 'react'
import { StyleSheet, View, Text, StatusBar } from 'react-native'
import Header from '../components/Header'

export default Login = () => {
  return (
    <View style={styles.fill}>
      <StatusBar translucent={true} barStyle={'dark-content'} backgroundColor={'transparent'} />
      <Header title="Register" fullScreen />
      <Text style={styles.text}>注册页面</Text>
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