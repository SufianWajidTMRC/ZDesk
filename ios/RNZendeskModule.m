//
//  ZendeskModule.m
//  RNSecondApp
//
//  Created by Faizan S on 29/09/2021.
//

#import "RNZendeskModule.h"
#import <React/RCTLog.h>
#import <ChatSDK/ChatSDK.h>
#import <ChatProvidersSDK/ChatProvidersSDK.h>
#import <MessagingSDK/MessagingSDK.h>

@implementation RNZendeskModule

RCT_EXPORT_METHOD(createCalendarEvent:(NSString *)name location:(NSString *)location)
{
 RCTLogInfo(@"Pretending to create an event %@ at %@", name, location);
}

RCT_EXPORT_METHOD(openZendesk:(NSString *)name email:(NSString *)email phone:(NSString *)phone)
{
  @try
  {
    dispatch_async(dispatch_get_main_queue(), ^{
      RCTLogInfo(@"Pretending to Open Zendesk for: Name = %@ -- Email = %@ -- Phone = %@", name, email, phone);
      

      
      ZDKChatFormConfiguration *formConfiguration = [[ZDKChatFormConfiguration alloc] initWithName:ZDKFormFieldStatusRequired
                                                                                             email:ZDKFormFieldStatusRequired
                                                                                       phoneNumber:ZDKFormFieldStatusOptional
                                                                                        department:ZDKFormFieldStatusOptional];
      
      ZDKChatAPIConfiguration *chatAPIConfiguration = [[ZDKChatAPIConfiguration alloc] init];
      chatAPIConfiguration.visitorInfo = [[ZDKVisitorInfo alloc] initWithName:name
                                                                        email:email
                                                                  phoneNumber:phone];
      ZDKChat.instance.configuration = chatAPIConfiguration;

      ZDKChatConfiguration *chatConfiguration = [[ZDKChatConfiguration alloc] init];
      //      chatConfiguration.isPreChatFormEnabled = NO;
            chatConfiguration.preChatFormConfiguration = formConfiguration;
      NSError *error = nil;
      NSArray *engines = @[
        [ZDKChatEngine engineAndReturnError:&error]
      ];
      if (!!error) {
        NSLog(@"[RNZendeskChatModule] Internal Error loading ZDKChatEngine %@", error);
        return;
      }
//      UIViewController *zendeskVC = [[ZDKMessaging instance] buildUIWithEngines:@[(id <ZDKEngine>) [ZDKChatEngine engineAndReturnError:nil]] configs:@[chatConfiguration] error:&error];
      UIViewController *zendeskVC = [[ZDKMessaging instance] buildUIWithEngines:engines configs:@[chatConfiguration] error:&error];
      
      if (!!error) {
        NSLog(@"[RNZendeskChatModule] Internal Error building ZDKMessagingUI %@",error);
        return;
      }
      NSDictionary *options = [[NSDictionary alloc] initWithObjectsAndKeys:
                                                    @"Dismiss", @"localizedDismissButtonTitle", nil];
      zendeskVC.navigationItem.leftBarButtonItem = [[UIBarButtonItem alloc] initWithTitle: options[@"localizedDismissButtonTitle"] ?: @"Close"
                                                                                    style: UIBarButtonItemStylePlain
                                                                                   target: self
                                                                                   action: @selector(dismissChatUI)];
      
      UINavigationController *chatController = [[UINavigationController alloc] initWithRootViewController: zendeskVC];
      [RCTPresentedViewController() presentViewController:chatController animated:YES completion:nil];
      
      
      
      
      
////      UIViewController *zendeskVC = [[ZDKMessaging instance] buildUIWithEngines:@[(id <ZDKEngine>) [ZDKChatEngine engineAndReturnError:nil]] configs:@[(id <ZDKConfiguration>) [[ZDKChatConfiguration alloc] init]] error:nil];
//      [[UIApplication sharedApplication].delegate.window.rootViewController presentViewController:zendeskVC animated:YES completion:nil];
      
      
    });
  }
  @catch(NSException *exception) {
    //Do nothing, obviously it wasn't attached because an exception was thrown.
    
    NSLog(@"Exception Name: %@ ",exception.name);
    NSLog(@"ExceptionReason: %@ ",exception.reason);
    
  }
}

- (void) dismissChatUI {
  [RCTPresentedViewController() dismissViewControllerAnimated:YES completion:nil];
}

// To export a module named RCTCalendarModule
RCT_EXPORT_MODULE(RNZendeskModule);
@end
