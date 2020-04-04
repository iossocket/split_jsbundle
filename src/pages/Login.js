import React from 'react'
import { StyleSheet, View, Button, StatusBar } from 'react-native'
import Header from '../components/Header'

export default Login = (props) => {
  return (
    <View style={styles.fill}>
      <StatusBar translucent={true} barStyle={'dark-content'} backgroundColor={'transparent'} />
      <Header title="我的" style={styles.header} fullScreen />
      <View style={styles.buttonWrapper}>
        <Button
          title="点击注册"
          onPress={() => props.navigation.push('Register')}
          color="#437dff"
        />
      </View>
    </View>
  )
}

const styles = StyleSheet.create({
  fill: {
    flex: 1
  },
  header: {
    backgroundColor: '#437dff',
  },
  buttonWrapper: {
    padding: 16
  }
})