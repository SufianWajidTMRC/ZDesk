import {StatusBar} from 'expo-status-bar';
import React, {useState} from 'react';
import {
  StyleSheet,
  Text,
  View,
  SafeAreaView,
  Button,
  NativeModules,
  TextInput,
  FlatList,
} from 'react-native';

export default function App() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');

  const nameInputHandler = enteredText => {
    setName(enteredText);
  };

  const emailInputHandler = enteredText => {
    setEmail(enteredText);
  };

  const phoneInputHandler = enteredText => {
    setPhone(enteredText);
  };


  // const Intercom = NativeModules.MyIntercomModule;
  // const {MyIntercomModule} = NativeModules
  // const { Zendesk } = NativeModules;
  
  const Zendesk = NativeModules.RNZendeskModule;

  return (
    <SafeAreaView style={styles.safeArea}>
      <View
        style={{
          height: 56,
          backgroundColor: '#2271b1',
          paddingTop: 2,
          alignItems: 'center',
          justifyContent: 'center',
        }}>
        <Text style={{fontSize: 18}}>Main Activity</Text>
      </View>
      <View
        style={{
          height: 56,
          paddingTop: 2,
          alignItems: 'center',
          flexDirection: 'row',
        }}>
        <TextInput
          placeholder="Enter Name"
          style={styles.topTextInput}
          value={name}
          onChangeText={nameInputHandler}
        />
      </View>
      <View
        style={{
          height: 56,
          paddingTop: 2,
          alignItems: 'center',
          flexDirection: 'row',
        }}>
        <TextInput
          placeholder="Enter Email"
          style={styles.topTextInput}
          value={email}
          onChangeText={emailInputHandler}
        />
      </View>
      <View
        style={{
          height: 56,
          paddingTop: 2,
          alignItems: 'center',
          flexDirection: 'row',
        }}>
        <TextInput
          placeholder="Enter Phone"
          style={styles.topTextInput}
          value={phone}
          onChangeText={phoneInputHandler}
        />
      </View>

<View style={{margin: 50}}>
  <Button
    title="Open ZendeskChat"
    onPress={() => {
      
      Zendesk.openZendesk(name, email, phone);
    }}
  />
</View>

</SafeAreaView>

  );
}

const styles = StyleSheet.create({
  safeArea: {
    // paddingLeft: 30,
    // paddingRight: 30,
    // paddingTop: 50,
    paddingBottom: 30,
    flex: 1,
    flexDirection: 'column',
  },
  topTextInput: {
    marginTop: 10,
    marginLeft: 30,
    marginRight: 30,
    paddingLeft: 2,
    borderBottomColor: 'black',
    borderBottomWidth: 1,
    flex: 1,
  },
  flatListParent: {
    marginTop: 10,
    marginBottom: 10,
    backgroundColor: '#eee',
    flex: 1,
  },
  // container: {
  //   flex: 1,
  //   backgroundColor: "#fff",
  //   alignItems: "center",
  //   justifyContent: "center",
  // },
});
