import React, {useState} from 'react';
import {
  Text,
  View,
  TextInput,
  Button,
  StyleSheet,
  NativeModules,
} from 'react-native';

const HelloWorld = props => {
  const [outputText, setOutputText] = useState('Hello World!!');
  return (
    <View style={styles.safeArea}>
      <Text>{outputText}</Text>
      {/* Wrapping button with a View for margin as we don't have style option for Button */}
      <View style={{margin: 10}}>
        <Button
          title="Change Text"
          onPress={() => setOutputText('Text changed')}
        />
      </View>
      <View style={{margin: 10}}>
        <Button
          title="Prev Text"
          onPress={() => {
            setOutputText('Hello World!');
          }}
        />
      </View>
      <TextInput placeholder="Enter Something" style={styles.topTextInput} />
    </View>
  );
};

const styles = StyleSheet.create({
  safeArea: {
    paddingLeft: 30,
    paddingRight: 30,
    paddingTop: 50,
    paddingBottom: 30,
    flex: 1,
    flexDirection: 'column',
  },
  topTextInput: {
    marginTop: 10,
    paddingLeft: 2,
    borderBottomColor: 'black',
    borderBottomWidth: 1,
  },
});

export default HelloWorld;
