import React from 'react'
import { StyleSheet, View, Button, StatusBar } from 'react-native'
import Header from '../components/Header'

export default My = (props) => {
  return (
    <View style={styles.fill}>
      <StatusBar translucent={true} barStyle={'light-content'} backgroundColor={'transparent'} />
      <Header title="Me" style={styles.header} fullScreen />
      <View style={styles.buttonWrapper}>
        <Button
          title="退出登录"
          onPress={() => props.navigation.push('Login')}
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
  text: {
    fontSize: 20,
    fontWeight: '500',
    color: '#437dff',
    textAlign: 'center'
  },
  header: {
    backgroundColor: '#437dff',
  },
  buttonWrapper: {
    padding: 16
  }
})